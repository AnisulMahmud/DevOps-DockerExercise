from flask import Flask, jsonify
import socket
import subprocess
from datetime import datetime
import os

app = Flask(__name__)

@app.route('/')
def service2_info():
    info = []




    # IP address
    hostname = socket.gethostname()
    ip_address = socket.gethostbyname(hostname)
    info.append(f"IP address: {ip_address}")

    # Process list those are running 
    process = subprocess.check_output(['ps', '-eo', 'pid,user,time,comm'])
    process_list = process.decode('utf-8')
    info.append(f"Processes:\n{process_list}")

    
    # Available disk
    statvfs = os.statvfs('/')
    free = statvfs.f_frsize * statvfs.f_bavail
    free_gb = free / (1024 * 1024 * 1024)  
    info.append(f"Free space: {free_gb:.2f} GB")

    # Last boot time
    boot_time = subprocess.check_output(['uptime', '-s'])
    last_boot_time = boot_time.decode('utf-8').strip()
    last_boot_time_formatted = datetime.strptime(last_boot_time, '%Y-%m-%d %H:%M:%S').strftime('%a %b %d %H:%M:%S GMT %Y')
    info.append(f"Last Boot time: {last_boot_time_formatted}")




    return "\n".join(info)


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)

    

