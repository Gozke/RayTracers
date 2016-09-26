package edu.gozke.jtracer.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import edu.gozke.jtracer.RenderOptions;
import edu.gozke.jtracer.RenderableScene;
import edu.gozke.jtracer.core.Color;


public class TracerFrame extends JFrame {
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JButton btnRenderScene;
	JCheckBox chckbxParalellProcessing;
	transient JPanel ImagePanel;
	transient private RenderableScene rendererBackend;

	private enum ImagePaneState {
		WELCOME, RENDERING, RENDER_COMPLETE, ERROR
	}

	public TracerFrame(RenderableScene rendererBackend) throws HeadlessException {
		super();
		this.rendererBackend = rendererBackend;
		initGUI();
	}

	private void setImagePanelContent(ImagePaneState state, byte[] imgData) {
		ImagePanel.removeAll();
		if (state == ImagePaneState.WELCOME) {
			JLabel lbl = new JLabel("Set the parameters and press Render scene!");
			ImagePanel.add(lbl);
		} else if (state == ImagePaneState.RENDERING) {
			JLabel lbl = new JLabel("Rendering scene...");
			ImagePanel.add(lbl);
			JProgressBar progressBar = new JProgressBar();
			progressBar.setIndeterminate(true);
			ImagePanel.add(progressBar);
		} else if(state == ImagePaneState.RENDER_COMPLETE){
			ImgDisplayer img = new ImgDisplayer(createBufferedImage(imgData));
			img.setPreferredSize(new Dimension(800,600));
			ImagePanel.add(img);
		} else if(state == ImagePaneState.ERROR){
			JLabel lbl = new JLabel("Exception occured while rendering. See stderr!");
			ImagePanel.add(lbl);
		} 
		
		repaint();
		revalidate();
		pack();
	}

	private void setEnableControls(boolean enabled){
		txtHeight.setEnabled(enabled);
		txtWidth.setEnabled(enabled);
		btnRenderScene.setEnabled(enabled);
		chckbxParalellProcessing.setEnabled(enabled);
	}
	
	protected void initGUI() {
		initWindow();
		
		getContentPane().setLayout(new BorderLayout(0, 5));

		ImagePanel = new JPanel();
		ImagePanel.setMinimumSize(new Dimension(10, 500));
		//ImagePanel.setPreferredSize(new Dimension(1024, 600));
		ImagePanel.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 3));
		getContentPane().add(ImagePanel, BorderLayout.CENTER);
		ImagePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		setImagePanelContent(ImagePaneState.WELCOME, null);

		JPanel ControlsPanel = new JPanel();
		getContentPane().add(ControlsPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_ControlsPanel = new GridBagLayout();
		gbl_ControlsPanel.columnWidths = new int[] { 51, 43, 0, 0, 0 };
		gbl_ControlsPanel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_ControlsPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_ControlsPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		ControlsPanel.setLayout(gbl_ControlsPanel);

		JLabel lblResolution = new JLabel("Resolution");
		GridBagConstraints gbc_lblResolution = new GridBagConstraints();
		gbc_lblResolution.gridwidth = 2;
		gbc_lblResolution.insets = new Insets(0, 0, 5, 5);
		gbc_lblResolution.gridx = 0;
		gbc_lblResolution.gridy = 0;
		ControlsPanel.add(lblResolution, gbc_lblResolution);

		JLabel lblWidth = new JLabel("Width");
		GridBagConstraints gbc_lblWidth = new GridBagConstraints();
		gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
		gbc_lblWidth.anchor = GridBagConstraints.EAST;
		gbc_lblWidth.gridx = 0;
		gbc_lblWidth.gridy = 1;
		ControlsPanel.add(lblWidth, gbc_lblWidth);

		txtWidth = new JTextField();
		txtWidth.setText("800");
		GridBagConstraints gbc_txtWidth = new GridBagConstraints();
		gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtWidth.insets = new Insets(0, 0, 5, 5);
		gbc_txtWidth.gridx = 1;
		gbc_txtWidth.gridy = 1;
		ControlsPanel.add(txtWidth, gbc_txtWidth);
		txtWidth.setColumns(3);

		chckbxParalellProcessing = new JCheckBox("Paralell processing");
		GridBagConstraints gbc_chckbxParalellProcessing = new GridBagConstraints();
		gbc_chckbxParalellProcessing.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxParalellProcessing.gridx = 2;
		gbc_chckbxParalellProcessing.gridy = 1;
		ControlsPanel.add(chckbxParalellProcessing, gbc_chckbxParalellProcessing);

		JLabel lblHeight = new JLabel("Hieght");
		GridBagConstraints gbc_height = new GridBagConstraints();
		gbc_height.anchor = GridBagConstraints.EAST;
		gbc_height.insets = new Insets(0, 0, 5, 5);
		gbc_height.gridx = 0;
		gbc_height.gridy = 2;
		ControlsPanel.add(lblHeight, gbc_height);

		txtHeight = new JTextField();
		txtHeight.setText("600");
		txtHeight.setColumns(3);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		ControlsPanel.add(txtHeight, gbc_textField);

		JLabel lblRenderOptions = new JLabel("Render options");
		GridBagConstraints gbc_lblRenderOptions = new GridBagConstraints();
		gbc_lblRenderOptions.insets = new Insets(0, 0, 5, 5);
		gbc_lblRenderOptions.gridx = 2;
		gbc_lblRenderOptions.gridy = 0;
		ControlsPanel.add(lblRenderOptions, gbc_lblRenderOptions);

		btnRenderScene = new JButton("Render scene");
		btnRenderScene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initiateRendering();
			}
		});
		GridBagConstraints gbc_btnRenderScene = new GridBagConstraints();
		gbc_btnRenderScene.insets = new Insets(0, 0, 5, 0);
		gbc_btnRenderScene.gridx = 3;
		gbc_btnRenderScene.gridy = 2;
		ControlsPanel.add(btnRenderScene, gbc_btnRenderScene);

		pack();
	}

	/**
	 * Sets up the window. Creates controls and other stuff.
	 */
	public void initWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function starts the rendering of the scene and displays the result
	 * when it's done. Displays a progress bar while the operation is completed.
	 */
	private void initiateRendering() {
		int width = Integer.parseInt(txtWidth.getText());
		int height = Integer.parseInt(txtHeight.getText());
		setEnableControls(false);
		setImagePanelContent(ImagePaneState.RENDERING, null);
		
		new RenderingWorker(width, height, createOptionsFromGUI()).execute();
	}
	
	private BufferedImage createBufferedImage(byte[] imgData){
		BufferedImage bImage = new BufferedImage(800, 600, BufferedImage.TYPE_3BYTE_BGR);
		WritableRaster rast = bImage.getRaster();
		rast.setDataElements(0, 0, 800, 600, imgData);

		return bImage;
	}
	
	private RenderOptions createOptionsFromGUI(){
		return new RenderOptions().setIsParalellRenderingEnabled(chckbxParalellProcessing.isSelected());
	}

	private class RenderingWorker extends SwingWorker<byte[], Object>{
		private int resX, resY;
		private RenderOptions opts;
		
		
		public RenderingWorker(int resX, int resY, RenderOptions opts) {
			super();
			this.resX = resX;
			this.resY = resY;
			this.opts = opts;
		}


		@Override
		protected byte[] doInBackground() throws Exception {
			System.out.println("I'm doing stuff..");			
			
			return rendererBackend.renderedScene(resX, resY, opts);
		}


		@Override
		protected void done() {
			super.done();
			try {
				setEnableControls(true);
				setImagePanelContent(ImagePaneState.RENDER_COMPLETE, get());
			} catch (InterruptedException | ExecutionException e) {
				setImagePanelContent(ImagePaneState.ERROR, null);
				e.printStackTrace();
			}
			
		}
		
		
		
	}
}
