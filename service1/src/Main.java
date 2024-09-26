import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.*;
import java.util.Date;
import java.util.stream.*;


public class Main {
    public static void main(String[] args) throws Exception {
        // for creating http server to listen on port 8199
        // ServerSocket serverSocket = new ServerSocket(8199, 50, InetAddress.getByName("0.0.0.0"));
        ServerSocket serverSocket = new ServerSocket(8199);
        System.err.println("Service1 is running on port 8199");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            OutputStream output = clientSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            // getting own information
            String service1 = getService1Info();

            // http request
            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/plain");
            writer.println("Connection: close");
            writer.println();
            writer.println("Service1 information:");
            writer.println(service1);
            writer.close();
            clientSocket.close();
        }
    }

    private static String getService1Info() throws Exception {
        StringBuilder info = new StringBuilder();

        // ip address
        InetAddress ip = InetAddress.getLocalHost();
        info.append("Ip Address: ").append(ip.getHostAddress()).append("\n");

        // Running processes
        ProcessBuilder processBuilder = new ProcessBuilder("ps", "ax");
        Process process = processBuilder.start();
        info.append("Processes:\n").append(outputFromProcess(process)).append("\n");

        // Available disk
        File root = new File("/");
        long free = root.getFreeSpace();
        double freeGB = free / 1e9; // Convert bytes to gigabytes
        info.append("Free space: ").append(String.format("%.2f", freeGB)).append(" GB\n");

        // // Last boot time
        // ProcessBuilder processBuilder2 = new ProcessBuilder("sh", "-c", "uptime -p");
        // Process process2 = processBuilder2.start();
        // String lastBootTime = outputFromProcess(process2);
        // if (lastBootTime.isEmpty()) {
        //     info.append("Last Boot time: N/A\n");
        //     System.err.println("Failed to retrieve last boot time.");
        // } else {
        //     info.append("Last Boot time: ").append(lastBootTime).append("\n");
        // }

        // Last boot time using RuntimeMXBean
        RuntimeMXBean runtimeMX = ManagementFactory.getRuntimeMXBean();
        Date startTime = new Date(runtimeMX.getStartTime());
        info.append("Last Boot time: ").append(startTime).append("\n");


        return info.toString();
    }

    private static String outputFromProcess(Process pro) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
        return reader.lines().collect(Collectors.joining("\n"));
    }
}