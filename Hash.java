package practice_5_baby_blockchain;

import java.io.UnsupportedEncodingException;

public class Hash {
	
	// ������� ����������� GetSHA1Hash ���������� ������������� ������ � ����������������� �������
		public static String toSHA1(String message) throws UnsupportedEncodingException {
			
			//�������������� ������� ������ ��������� � �������� ���
			byte [] bytes = message.getBytes();
			String binaryCode = "";

			for (int i = 0; i < bytes.length; i++) {			
				if (bytes[i] < 0) {
					binaryCode = binaryCode + String.format("%8s", Integer.toBinaryString(bytes[i] ^ -1<<8)).replace(' ', '0');
				} else {
					binaryCode = binaryCode + String.format("%8s", Integer.toBinaryString(bytes[i] & 0xFF)).replace(' ', '0');
				}
			}
			
			// ���������� ������ �������������� ��� � ���� ������
			String hash = "";
			// ���������� ���������� ��� ���������� �������� ��������		
			int a;
			int b;
			int c;
			int d;
			int e;
			int f = 0;
			int k = 0;
			int temp;
			
			// ����� ���������, ���������������� � �������� ���
			int l = binaryCode.length();
			
			// ����������� ������� � ��������� � ���� ��������� ����
			String binarycodeWithOne = binaryCode + "1";
			
			// ��������� �����, �������� 512 �����
			int y = (l+1+64)%512;
			int x = 512 - y;
			
			// ���������� ����� ����������� �����, ������ � 
			for (int i = 1; i <= x; i++) {
				binarycodeWithOne = binarycodeWithOne + "0";		
			}
			
			// ���������� 64 ���� � ����� ������ � �������� �������
			int byteLength = binaryCode.length();
			String s1 = String.format("%64s", Integer.toBinaryString(byteLength)).replace(' ', '0');
			
			// ���������� ������ ������������ ������� ������ � �������� ������� 
			String preprocessingStr = binarycodeWithOne + s1;
			// ��������� ������ ������ ��������� �������� 512 ���
			String[] block512 = new String [preprocessingStr.length()/512];
			// ������ ��� �������� ����� �������� 32 ���
			String w32 = "";
			
			// ����������� ��������
			int intH0 = 0x67452301;
	        int intH1 = 0xEFCDAB89;
	        int intH2 = 0x98BADCFE;
	        int intH3 = 0x10325476;
	        int intH4 = 0xC3D2E1F0;
					
			// ���� ��������� ������������ ������� ������ �� ��������� �������� �� 512 ��� ������
		    for (int i = 0; i < block512.length; i++) {
		    	block512[i] = preprocessingStr.substring(i*512, (i+1)*512);
		    	int binaryToDec[] = new int[80];
		    	// ���� ��������� ������ �������� 512 ��� �� ��������� �������� �� 32 ����
		    	for (int j = 0; j < 16; j++) {
			    	w32 = block512[i].substring(j*32, (j+1)*32);
			    	binaryToDec[j] = Integer.parseUnsignedInt(w32, 2);
			    }
		    	
		    	for (int j = 16; j < 80; j++) {
		    		binaryToDec[j] = Integer.rotateLeft(binaryToDec[j-3] ^ binaryToDec[j-8] ^ binaryToDec[j-14] ^ binaryToDec[j-16], 1);	
		    	}
		    				
				// ������������� a, b, c, d, e ������������ ����������
				a = intH0;
				b = intH1;
				c = intH2;
				d = intH3;
				e = intH4;
				
				// ������������ ������� ���������
				for (int j = 0; j < 80; j++) {
					 if (j <= 19) {
				            f = (b & c) | ((~b) & d);
				            k = 0x5A827999;
					 } else if (j <= 39) {
				            f = b ^ c ^ d;
				            k = 0x6ED9EBA1;
				     } else if (j <= 59) {
				            f = (b & c) | (b & d) | (c & d);
				            k = 0x8F1BBCDC;
				     } else if (j <= 79) {
				            f = b ^ c ^ d;
				            k = 0xCA62C1D6; 
				     }
					 
				     temp = Integer.rotateLeft(a, 5) + f + e + k + binaryToDec[j];
				     e = d;
				     d = c;
				     c = Integer.rotateLeft(b, 30);
				     b = a;
				     a = temp;	
				}
				
				// ���������� ������������� �������� ��������
				 intH0 = intH0 + a;
				 intH1 = intH1 + b;
				 intH2 = intH2 + c;
				 intH3 = intH3 + d;
				 intH4 = intH4 + e;
		    }
		    String h1Length = Integer.toHexString(intH0);
	        String h2Length = Integer.toHexString(intH1);
	        String h3Length = Integer.toHexString(intH2);
	        String h4Length = Integer.toHexString(intH3);
	        String h5Length = Integer.toHexString(intH4);

	        //Integer.toHexString �� �������� �������������� ��������� �����
	        if(h1Length.length() < 8) {
	            StringBuilder h1L = new StringBuilder(h1Length);
	            h1L.insert(0,0);
	            h1Length = h1L.toString();
	        };
	        if(h2Length.length() < 8) {
	            StringBuilder h2L = new StringBuilder(h2Length);
	            h2L.insert(0,0);
	            h2Length = h2L.toString();
	        };
	        if(h3Length.length() < 8) {
	            StringBuilder h3L = new StringBuilder(h3Length);
	            h3L.insert(0,0);
	            h3Length = h3L.toString();
	        };
	        if(h4Length.length() < 8) {
	            StringBuilder h4L = new StringBuilder(h4Length);
	            h4L.insert(0,0);
	            h4Length = h4L.toString();
	        };
	        if(h5Length.length() < 8) {
	            StringBuilder h5L = new StringBuilder(h5Length);
	            h5L.insert(0,0);
	            h5Length = h5L.toString();
	        };

	        //���������
	        hash = h1Length + h2Length + h3Length + h4Length + h5Length;
		    return hash;
		    
		}

}
