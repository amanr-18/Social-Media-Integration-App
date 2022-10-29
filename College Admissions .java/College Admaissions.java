



import java.io.DataInputStream;

import java.io.PrintWriter;

import java.io.IOException;



public class Main {

    

  
    public static void main(String[] args) throws IllegalArgumentException {

        initializeIO();

        try {

            int times = nextInt();

        

        while(times -- > 0) {

            int[] cr = new int[nextInt()], sr = new int[nextInt()], colleges = new int[cr.length];

            //Storing college ranks

            for(int i = 0; i < cr.length; i ++) {

                colleges[i] = nextInt();

                cr[colleges[i] - 1] = i + 1;

            }

            //Storing student ranks

            int pos = nextInt();

            sr[pos - 1] = 1;

            for(int i = 1; i < sr.length; i ++) {

                sr[nextInt() - 1] = i + 1;

                

            }

           

            int[][] selection_list = new int[sr.length][];

            for(int i = 0; i < sr.length; i ++) {

                selection_list[i] = new int[nextInt()];

                for(int j = 0; j < selection_list[i].length; j ++) {

                    selection_list[i][j] = nextInt();

                }

            }

       

            int[][] comp = new int[pos][];

            for(int i = 0; i < pos; i ++) {

                comp[i] = selection_list[sr[i] - 1];

            }

           

            boolean found = false;

            for(int i = 0; i < comp.length; i ++) {

                int gr = cr.length + 1;

                int rank = 0, college = 0;

                

                for(int j = 0; j < comp[i].length; j ++) {

                    rank = colleges[comp[i][j] - 1];

                    if(cr[rank - 1] > 0 && rank < gr) {

                        gr = rank;

                        college = comp[i][j];

                    }

                }

                    

                if(college > 0) {

                    if(i == pos - 1) {

                        out.println(college);

                        found = true;

                    }

                    cr[colleges[college - 1] - 1] = 0;

                }

            }

                

                

            if(!found) {

                out.println(0);

            }

            

        }

        

        } catch(IllegalArgumentException iae) {

            close();

            throw iae;

        }

        

        close();

    }

    

    

    

    

    

    

    

    

  

    private static DataInputStream dis;

   

    private static byte[] buffer;

  

    private static int bytesRead, bufferPos;



    private static PrintWriter out;

    


    private static void initializeIO() {

        dis = new DataInputStream(System.in);

        buffer = new byte[1 << 16];

        

        out = new PrintWriter(System.out);

    }

    



    private static void close() {

        try {

            out.flush();

            dis.close();

            out.close();

        }

        catch(IOException e) {

        }

    }

    

  
    private static byte c;

  
    private static boolean neg;

    

  

    private static int nextInt() throws IllegalArgumentException {

        int temp = 0;

        skipSpace();

        

        neg = (c == '-');

        if(neg) {

            read();

        }



   	   	do {

   	   	    if(!isDigit()) {

   	   	        throw new IllegalArgumentException();

   	   	    }

   	   	    

     		temp = temp * 10 + c - '0';

    		read();

     	} while((c != -1) && !(c == ' ' || c == '\n' || c == '\r'));

        

        return (neg) ? -temp : temp;

    }

    


    private static long nextLong() throws IllegalArgumentException {

		long temp = 0;

		skipSpace();

		

		neg = (c == '-');

		if (neg) {

			read();

		}

		

   	   	do {

   	   	    if(!isDigit()) {

   	   	        throw new IllegalArgumentException();

   	   	    }

   	   	    

     		temp = temp * 10L + c - '0';

    		read();

     	} while((c != -1) && !(c == ' ' || c == '\n' || c == '\r'));



		return (neg) ? -temp : temp;

	}

	

	private static boolean isDigit() {

	    return (c >= '0' && c <= '9');

	}

    

   

    private static int count; 



    private static byte[] buff;

    

    private static String nextLine(int maxLength) throws IllegalArgumentException {

        if(buff == null || buff.length < maxLength) {

            buff = new byte[maxLength];

        }

        

        count = 0;

        skipSpace();

        

        while(c != -1) {

            if(c == '\n' || c == '\r') {

                break;

            }

            

            buff[count ++] = c;

            read();

        }

        

        if(count == 0) {

            throw new IllegalArgumentException();

        }

        

        return new String(buff, 0, count);

    }

    

   

    private static String next(int maxLength) throws IllegalArgumentException {

        if(buff == null || buff.length < maxLength) {

            buff = new byte[maxLength];

        }

        

        count = 0;

        skipSpace();

        

        while(c != -1) {

            if(c == ' ' || c == '\n' || c == '\r') {

                break;

            }

            

            buff[count ++] = c;

            read();

        }

        

        if(count == 0) {

            throw new IllegalArgumentException();

        }

        

        return new String(buff, 0, count);

    }

   

    private static void skipSpace() {

        do {

            read();

        } while((c != -1) && (c == ' ' || c == '\n' || c == '\r'));

    }

    

   
    private static void skipLine() {

        do {

            read();

        } while(c != -1 && c != '\n' && c != '\r');

    }

    

  

    private static void fillBuffer() throws IOException {

        bytesRead = dis.read(buffer, bufferPos = 0, buffer.length);

        

        if(bytesRead == -1) {

            buffer[0] = -1;

        }

    }

   

   

    private static void read() {

        if(bufferPos >= bytesRead) {

            try {

                fillBuffer();

            }

            catch(IOException e) {

            }

        }

        

        c = buffer[bufferPos ++];

    }

}
