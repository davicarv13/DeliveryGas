package view.gas;

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

import controller.ControllerGas;
import model.entities.Gas;

public class ViewGasDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ControllerGas gasController = new ControllerGas();

	/**
	 * Launch the application.
	 */
	public void NewViewGasDelete() {
		try {
			ViewGasDelete dialog = new ViewGasDelete();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewGasDelete() {
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCodGas = new JLabel("Selecione o código do gás");
		lblCodGas.setBounds(142, 65, 189, 16);
		contentPanel.add(lblCodGas);
		
		JComboBox comboBoxCodGas = new JComboBox();
		comboBoxCodGas.setBounds(142, 96, 154, 25);
		contentPanel.add(comboBoxCodGas);
		
		atualizaComboBox(comboBoxCodGas);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(172, 133, 98, 26);
		contentPanel.add(btnExcluir);
		{
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(gasController.deleteById((Integer)comboBoxCodGas.getSelectedItem()) == true){
						atualizaComboBox(comboBoxCodGas);
						JOptionPane.showMessageDialog(null, "Gás excluído", "Gas", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Gás não excluído", "Gas", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
	}
	
	public void atualizaComboBox(JComboBox comboBoxCodGas){
		comboBoxCodGas.removeAllItems();
		List<Gas> listGas = gasController.findAll();
		
		for(Gas percorrer : listGas){
			comboBoxCodGas.addItem(percorrer.getCodGas());
		}
	}

}
