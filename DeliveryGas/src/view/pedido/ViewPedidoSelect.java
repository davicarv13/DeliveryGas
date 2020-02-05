package view.pedido;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerPedido;
import model.entities.Pedido;

public class ViewPedidoSelect extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JList<Pedido> list = new JList<Pedido>();
	ControllerPedido pedidoController = new ControllerPedido();

	/**
	 * Launch the application.
	 */
	public void NewViewPedidoSelect() {
		try {
			ViewPedidoSelect dialog = new ViewPedidoSelect();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewPedidoSelect() {
		setBounds(100, 100, 780, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		list.setBounds(12, 0, 780, 600);
		contentPanel.add(list);
		contentPanel.setLayout(null);
		{
			List<Pedido> listPedido = pedidoController.findAll();
			
			DefaultListModel<Pedido> listModel = new DefaultListModel<Pedido>();
	        
			for (Pedido percorrer : listPedido) {
				listModel.addElement(percorrer);
			}
			list.setModel(listModel);
		}
	}

}
