package practice;

public class Primitive {

	

		public static void main(String[] args) {
			char a = '0';
			int c = (int)a;
			long l = c; // Widening Cast
			byte b = (byte) l; //Narrowing Cast
			short s = b;
			
			System.out.println((int)s);
			
			A aType = new B();
			Object object = new B();
			//object.substruction(4, 2);
			B bType = (B) object;
			bType.substruction(4, 2);
			
			boolean isInstance = aType instanceof A;
			System.err.println(isInstance);
		}
		
		public interface A{
			int add(int a, int b);
		}
		
		public static class B implements A{

			@Override
			public int add(int a, int b) {
				return a+b;
			}
			
			public int substruction(int a, int b) {
				return a-b;
			}
		}
	
	}


