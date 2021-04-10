package employee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManagerImpl implements Manager {
	BufferedReader br;
	BufferedWriter bw;
	FileReader fr;
	FileWriter fw;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	FileOutputStream fos;
	FileInputStream fis;
	File file;
	List<Employee> eList = new ArrayList<>();
	
	/////////////////////////////////////////////////
	////////// �̱������� �����ϼ��� ///////////
	///////////////////////////////////////////////
	
	private static EmployeeManagerImpl singletone = new EmployeeManagerImpl();
	public static EmployeeManagerImpl getInstnace() {
		return singletone;
	}
	private EmployeeManagerImpl() {
		file = new File("employee.txt");
		if (file.exists()) {
			getFile();
		} else {
			// 1. ������ �������� �ʴ´ٸ�...������ �������� �Ѵ�.
			try {
				if(file.createNewFile()) System.out.println("���� ���� ����");
			}catch(IOException e) {
				System.out.println("���� ���� ����...");
			}
		}
	}

	public void getFile(){
		// 2. ������ �����Ѵٸ�....
		/*
		 * ObjectInputStream�� ���� .... new ObjectInputStream()
		 * ���Ͽ� �ִ� ������ �о�ͼ� Employee�� �����ϰ� ������ Employee���� List�� �����Ѵ�....readObject()
		 */
		
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			eList = (ArrayList<Employee>)ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				if (br != null)
					br.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void saveFile() {
		/*
		 * 3. ���α׷� ����� �����ϴ� ���.
		 *    ���α׷� ���� ������ ArrayList�� ����� ��� ����� ���� ������ ���Ͽ� ����Ѵ�.
		 *    new ObjectOutputStream() ����...writeObject()����ؼ� ����ȭ
		 */
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(eList);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public boolean insertEmployee(Employee employee) {
		// eList �� ������ �߰��Ѵ�.
		return eList.add(employee);
	}

	@Override
	public boolean deleteEmployee(String eid) {
		// eList�� ����� �����߿��� �ش��ϴ� ������ ����
		for(Employee e : eList) {
			if(e.getEid().equals(eid)) {
				eList.remove(e);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Employee findEmployee(String eid) {
		// Ư�� ������ �˻��ϴ� ���
		for(Employee e : eList) {
			if(e.getEid().equals(eid)) return e;
		}
		
		return null;
	}

	@Override
	public void showAll() {
		//
		for(Employee e : eList) System.out.println(e);
	}
}