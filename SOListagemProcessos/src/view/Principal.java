package view;

import javax.swing.JOptionPane;

import controller.KillController;

public class Principal {

	public static void main(String[] args) {
		
		KillController kill = new KillController();
		
		
		
		int option = 0;
		
		do {
			try {
				option = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha uma op��o: \n 1 - Consultar Processos \n 2 - Matar processo por PID \n 3 - Matar processo por nome \n 0 - Sair"));
			} catch (Exception error) {
				JOptionPane.showMessageDialog(null, "Obrigado pela prefer�ncia");
				break;
			}
			
			switch (option) {
				case 1:
					kill.listProcess();
					break;
				case 2:
					String pid = JOptionPane.showInputDialog(null, "Insira o PID");
					kill.killProcessByPID(pid);
					break;
				case 3:
					String name = JOptionPane.showInputDialog(null, "Insira o nome do processo");
					kill.killProcessByName(name);
					break;
				case 0: 
					JOptionPane.showMessageDialog(null, "Obrigado pela prefer�ncia");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Op��o inv�lida");
			} 
			
		} while (option != 0);

	}

}
