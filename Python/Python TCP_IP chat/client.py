import socket
from threading import Thread

TCP_PORT = 5005
BUFFER_SIZE = 1024

USER_NAME = input('Digite o seu nome de usuário: ')
TCP_IP = input('Digite o endereço ip do servidor: ')
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((TCP_IP, TCP_PORT))

def getMessage():
    while True:
        data = s.recv(BUFFER_SIZE)
        if not data: continue
        print(data.decode('utf-8'))

thread = Thread(target=getMessage)
thread.start()

while True:
    MESSAGE = USER_NAME + ": " + input()
    s.send(MESSAGE.encode('utf-8'))

conn.close()