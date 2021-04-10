package exception.user.test;

import java.util.Scanner;

/*
 * ��������� Exception
 * 1. ���� Ŭ������ ���� ���� �ۼ��ؼ� ���
 * 	  class A extends Exception {  }
 * 2. ������ ������� ����
 * 	  Ư���� ������ �����Ǹ� ���ܸ� ���������� �������Ѽ� ���α׷��� �ڵ鸵�Ѵ�.
 * 
 * -------------------------------------
 * 	  ���ο�ȭ�� �����ϴµ�
 * 	  ���̰� 19���� �ȵǴ� ����� ���忡 �����ϴ� ���
 * 	  ������ ������� �ʰ� / 19�� �̻�Ǵ� ����� ��ȭ������
 * 	  ����Ҽ� �ִ� ������ �ۼ�..
 * 	  -->
 * 	  UnderAgeException�� �����ؼ� ����ϰڴ�.
 */
class UnderAgeException extends Exception {
	
	UnderAgeException(){
		this("19�� ���̰� �ȵǸ�  ��ȭ ���� �ȵ˴ϴ�.");
	}
	
	UnderAgeException(String message){
		super(message);
	}
}

class AdultMovieService {
	public void entrance(int age) throws UnderAgeException{
		if(age<19) throw new UnderAgeException();
		else System.out.println("Ticketing... Entrance OK!!!");
	}
}

public class AdultMovieExceptionTest3 {

	public static void main(String[] args) {
		System.out.println("���̸� �Է��ϼ���: ");
		Scanner sc = new Scanner(System.in);
		int age = sc.nextInt();
		
		AdultMovieService service = new AdultMovieService();
		try {
			service.entrance(age);
		}
		catch(UnderAgeException e) {
			System.out.println(e.getMessage());
		}
		
		
		

	}

}