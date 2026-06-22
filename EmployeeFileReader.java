package adjava;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class EmployeeFileReader {

	public static void main(String[] args) {
		

	




   
        String filePath = "D:\\java\\sample_data.txt";
        try {
        	
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String line;

            System.out.println("-------------------------------------------------------------");
            System.out.printf("%-5s %-15s %-10s %-30s%n",
                    "ID", "NAME", "SALARY", "DESIGNATION");
            System.out.println("-------------------------------------------------------------");

            while ((line = br.readLine()) != null) {
            	
            	 if (line.trim().isEmpty()) {
            	        continue;
            	    }

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int salary = Integer.parseInt(data[2]);
                String desig = data[3];

                System.out.printf("%-5d %-15s %-10d %-30s%n",
                        id, name, salary, desig);
            }

            System.out.println("-------------------------------------------------------------");

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
