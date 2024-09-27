from flask import Flask, jsonify
import socket
import subprocess

app = Flask(__name__)

@app.route('/')
def service2_info():
    info = {}


    # IP address
    hostname = socket.gethostname()
    ip_address = socket.gethostbyname(hostname)
    info["IP address"] = ip_address
    # info.append(f"IP address: {ip_address}")

    # Process list those are running ( )
    process = subprocess.check_output(['ps', 'aux'])
    process_list = process.decode('utf-8')
    info["Process list"] = process_list
    # info.append(f"Process list: {process_list}")

    # Available disk
    disk_space = subprocess.check_output(['df', '/'])
    disk_space_available = disk_space.decode('utf-8').split('\n')[1].split()[3]
    disk_space_gb = int(disk_space_available) / (1024*1024*1024)
    info["Available disk"] = f"{disk_space_gb:.2f} GB"
    # info.append(f"Available disk: {disk_space_gb : .2f} GB")


    # Last boot time
    boot_time = subprocess.check_output(['uptime', '-s'])
    last_boot_time = boot_time.decode('utf-8')
    info["Last boot time"] = last_boot_time
    # info.append(f"Last boot time: {last_boot_time}")


    return jsonify(info)


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)

    

