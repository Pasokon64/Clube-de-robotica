import RPi.GPIO as GPIO
from time import sleep
 
GPIO.setmode(GPIO.BOARD)
 
MotorA = 17
MotorB = 27
 
GPIO.setup(MotorA,GPIO.OUT)
GPIO.setup(MotorB,GPIO.OUT)
 
print "Frente"
GPIO.output(MotorA,GPIO.HIGH)
GPIO.output(MotorB,GPIO.LOW)
 
sleep(2)
 
print "Trás"
GPIO.output(MotorB,GPIO.LOW)
GPIO.output(MotorB,GPIO.HIGH)
sleep(2)

GPIO.cleanup()
