package view.armazem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ControllerArmazem;

public class ViewArmazemDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodArmazem;

	/**
	 * Launch the application.
	 */
	public void NewViewArmazemDelete() {
		try {
			ViewArmazemDelete dialog = new ViewArmazemDelete();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewArmazemDelete() {
		ControllerArmazem armazemController = new ControllerArmazem();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCodArmazem = new JLabel("Insira o código do armazem");
		lblCodArmazem.setBounds(133, 80, 174, 16);
		contentPanel.add(lblCodArmazem);
		
		txtCodArmazem = new JTextField();
		txtCodArmazem.setBounds(157, 108, 114, 20);
		contentPanel.add(txtCodArmazem);
		txtCodArmazem.setColumns(10);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(167, 136, 98, 26);
		contentPanel.add(btnExcluir);
		{
			
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(armazemController.deleteByID(Integer.parseInt(txtCodArmazem.getText())) == true){
						JOptionPane.showMessageDialog(null, "Armazem excluído", "Armazem", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Armazem não excluído", "Armazem", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
		}
	}
}
