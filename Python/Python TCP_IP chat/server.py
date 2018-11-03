import socket
from threading import Thread

TCP_PORT = 5005
BUFFER_SIZE = 1024  # Normally 1024, but we want fast response

print('Porta: 5005')
USER_NAME = input('Digite o seu nome de usuário: ')
TCP_IP = input('Digite o endereço de ip do servidor: ')

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((TCP_IP, TCP_PORT))

s.listen(1)
conn, addr = s.accept()
print('Connection address:', addr)

def getMessage():
    while True:
        data = conn.recv(BUFFER_SIZE)
        if not data: continue
        print(data.decode('utf-8'))

thread = Thread(target=getMessage)
thread.start()

while True:
    MESSAGE = USER_NAME + ": " + input()
    conn.send(MESSAGE.encode('utf-8'))

conn.close()
