import cv2
import numpy
from deepface import DeepFace
from playsound import playsound
import threading

# 0 just means the camera index - if you have multiple cameras, you'd specify by increasing the index
camera = cv2.VideoCapture(0)

lock = threading.Lock()
# Created a method to use as the threading target to ensure camera didn't freeze while playing a sound associated with the emotion
# placeholder is obv a placeholder, replaced by the currentEmotion arg later on in the thread
def playAudio(placeholder):
    with lock:
        if placeholder == 'sad':
            playsound('sad.mp3')
        elif placeholder == 'surprise':
            playsound('suprised.mp3')
    
# Checks to see if camera opened correctly. If so, sets up frame and return value (rval)
# If camera closes, sets values to False automatically
if camera.isOpened():
    rval, frame = camera.read()
else:
    rval = False

# Initilzies variable for storing current sound running
audio_thread = None

try:
    while rval: #if read is successfull (since both are set to same bool value)
        cv2.imshow("Press Esc to exit", frame) #open window with title of "preview", set as frame
        rval, frame = camera.read()
        
        # There are other modifiers we can pull for the camera, but for this project I only want the emotion and age
        facialExpression = DeepFace.analyze(frame, actions=['age','emotion'], enforce_detection=False)     
        
        # Settings these variables equal to facialExpression[0] pulls the first values out of these structures.
        # For 'dominant_expression', it's calculating and storing the highest change of the current emotion, so we just pull it and store it in currentEmotion
        # Same for age 
        if facialExpression:           
            currentEmotion = facialExpression[0]['dominant_emotion']
            guessAge = facialExpression[0]['age']
        else:
            currentEmotion = "No Face Detected!"
            guessAge = 0
        # Just creates the text and displays them on the 'frame'
        cv2.putText(frame, f"Emotion: {currentEmotion}", (20, 60), cv2.FONT_HERSHEY_SIMPLEX, 1, (40, 100, 30), 3)
        cv2.putText(frame, f"Age: {guessAge}", (40, 120), cv2.FONT_HERSHEY_COMPLEX, 1, (0, 0, 255), 3)
        
        # Generates the thread - threading allows for multiple processes to run in parallel. Without this, the camera would freeze each time a sound played.
        if audio_thread is None or not audio_thread.is_alive():
            # Sets the target of the thread to run in parallel with the playAudio function, passing in the currentEmotion from above to determine the correct sound file. 
            audio_thread = threading.Thread(target=playAudio, args=(currentEmotion,))
            # Setting the daemon to True makes it so the thread terminates when the function it's working with terminates. 
            audio_thread.daemon = True
            audio_thread.start()
            
        
        #waits 20 milliseconds for a key to be pressed each frame                         
        key = cv2.waitKey(20) 
        #if that key was 27 (Esc) then the loop terminates.
        if key == 27: 
            break
except Exception as t:
    print(f"No face detected. Error: {t}")
    
camera.release()
cv2.destroyWindow("Press Esc to exit")