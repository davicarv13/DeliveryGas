package view.entregador;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerEntregador;
import model.entities.Entregador;

public class ViewEntregadorDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public void NewViewEntregadorDelete() {
		try {
			ViewEntregadorDelete dialog = new ViewEntregadorDelete();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewEntregadorDelete() {
		ControllerEntregador EntregadorController = new ControllerEntregador();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCodEntregador = new JLabel("Selecione o CPF do Entregador");
		lblCodEntregador.setBounds(133, 80, 174, 16);
		contentPanel.add(lblCodEntregador);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(169, 143, 98, 26);
		contentPanel.add(btnExcluir);
		
		JComboBox comboBoxCpf = new JComboBox();
		comboBoxCpf.setBounds(103, 108, 239, 25);
		List<Entregador> listEntregador = EntregadorController.findAll();
		
		for(Entregador percorrer : listEntregador){
			comboBoxCpf.addItem(percorrer.getCpf());
		}
		
		contentPanel.add(comboBoxCpf);
		{
			
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object obj = comboBoxCpf.getSelectedItem();
					if(EntregadorController.deleteByCpf(obj.toString()) == true){
						JOptionPane.showMessageDialog(null, "Entregador excluído", "Entregador", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Entregador não excluído", "Entregador", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
		}
	}
}
