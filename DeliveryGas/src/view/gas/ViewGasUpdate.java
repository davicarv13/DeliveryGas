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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ControllerGas;
import model.entities.Gas;

public class ViewGasUpdate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTipo;
	private JTextField txtValor;
	private JComboBox comboBoxCodGas;
	private JLabel lblCodGas;
	private JButton btnAtualizar;

	/**
	 * Launch the application.
	 */
	public void NewViewGasUpdate() {
		try {
			ViewGasUpdate dialog = new ViewGasUpdate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewGasUpdate() {
		ControllerGas gasController = new ControllerGas();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(12, 12, 55, 16);
		contentPanel.add(lblTipo);
		
		txtTipo = new JTextField();
		txtTipo.setColumns(10);
		txtTipo.setBounds(12, 32, 104, 20);
		contentPanel.add(txtTipo);
		
		JLabel btnValor = new JLabel("Valor");
		btnValor.setBounds(145, 12, 55, 16);
		contentPanel.add(btnValor);
		
		txtValor = new JTextField();
		txtValor.setColumns(10);
		txtValor.setBounds(145, 32, 104, 20);
		contentPanel.add(txtValor);
		
		comboBoxCodGas = new JComboBox();
		comboBoxCodGas.setBounds(281, 30, 155, 25);
		contentPanel.add(comboBoxCodGas);
		
		List<Gas> listGas = gasController.findAll();
		
		for(Gas percorrer : listGas){
			comboBoxCodGas.addItem(percorrer.getCodGas());
		}
		
		lblCodGas = new JLabel("Selecione o código do gás");
		lblCodGas.setBounds(281, 12, 155, 16);
		contentPanel.add(lblCodGas);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(175, 210, 98, 26);
		contentPanel.add(btnAtualizar);
		{
			btnAtualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Gas obj = new Gas((Integer)comboBoxCodGas.getSelectedItem(), txtTipo.getText(), Double.parseDouble(txtValor.getText()), null);
					if(gasController.update(obj) == true){
						JOptionPane.showMessageDialog(null, "Gás atualizado", "Gas", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Gás não atualizado", "Gas", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
	}

}
