package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController {

	public KillController() {
		super();
	}

	public void listProcess() {
		try {
			String os = os();
			String process = os.contains("Windows") ? "TASKLIST /FO TABLE" : "ps -ef";
			Process p;

			if (os.contains("Windows")) {
				p = Runtime.getRuntime().exec(process);
			} else {
				p = Runtime.getRuntime().exec(new String[] { "/bin/bash", "-c", process });
			}

			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);

			String linha = buffer.readLine();
			StringBuffer processList = new StringBuffer();

			while (linha != null) {
				processList.append(linha + "\n");
				linha = buffer.readLine();
			}

			System.out.println(processList);

			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	public void killProcessByPID(String pid) {
		String os = os();
		String process = os.contains("Windows") ? "TASKKILL /PID " + pid : "kill -9 " + pid;

		callProcess(process);
	}

	public void killProcessByName(String name) {
		String os = os();
		String process = os.contains("Windows") ? "TASKKILL /IM " + name : "pkill -f \"" + name + "\"";

		callProcess(process);
	}

	private void callProcess(String process) {
		String os = os();

		try {
			if (os.contains("Windows")) {
				Runtime.getRuntime().exec(process);
			} else {
				Runtime.getRuntime().exec(new String[] { "/bin/bash", "-c", process });
			}

		} catch (IOException error) {
			String msgErro = error.getMessage();

			if (msgErro.contains("740")) {
				StringBuffer buffer = new StringBuffer();

				buffer.append("cmd /c ");
				buffer.append(process);

				try {
					if (os.contains("Windows")) {
						Runtime.getRuntime().exec(buffer.toString());
					} else {
						Runtime.getRuntime().exec(new String[] { "/bin/bash", "-c", buffer.toString() });
					}
				} catch (IOException error2) {
					error2.printStackTrace();
				}

			} else {
				error.printStackTrace();
			}
		}
	}

	private String os() {
		return System.getProperty("os.name");
	}
}
