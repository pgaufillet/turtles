/*******************************************************************************
 * Copyright (c) 2015, 2016 Pierre Gaufillet.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Pierre Gaufillet - initial API and implementation
 *******************************************************************************/
package lpdql.turtle.view.editors;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;

import lpdql.turtle.view.TurtleCmdParserException;
import lpdql.turtle.view.parts.TurtleEditPartFactory;
import turtle.SquareWorld;
import turtle.Turtle;

/**
 * Main class for the Turtle World editor
 *
 */
public class TurtleEditor extends GraphicalEditor {

	private Resource turtleRes;

	private SquareWorld squareWorld;

	public TurtleEditor() {
		// IMPORTANT: we set here the domain that may be shared with other
		// editors / tools.
		// In this simplistic case with no sharing, a default EditDomain is
		// sufficient.
		setEditDomain(new DefaultEditDomain(this));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#createGraphicalViewer(org.
	 * eclipse.swt.widgets.Composite)
	 */
	protected void createGraphicalViewer(Composite parent) {
		GridLayout gridLayout = new GridLayout(1, false);
		parent.setLayout(gridLayout);
		Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
		Composite compositeText = new Composite(parent, SWT.NONE);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		compositeText.setLayoutData(data);
		text.setLayoutData(data);
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		data = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		composite.setLayoutData(data);
		GraphicalViewer viewer = new ScrollingGraphicalViewer();
		viewer.createControl(composite);
		text.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// Do nothing
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// When Return is pressed, parse the command
				if (e.character == '\r') {
					Object src = e.getSource();
					if (src instanceof Text) {
						Text text = (Text) src;
						String cmdText = text.getText();
						if (parse(cmdText)) {
							text.setText("");
						}
					}
				}
			}

		});
		setGraphicalViewer(viewer);
		configureGraphicalViewer();
		hookGraphicalViewer();
		initializeGraphicalViewer();
	}

	/**
	 * Parse cmdText and updates the model as needed. Throws the exception
	 * TurtleCmdParserException if the command is incorrect.
	 * 
	 * @param cmdText
	 * @return
	 */
	private boolean parse(String cmdText) {
		// Split the input into tokens separated by blanks
		String[] token = cmdText.split("[\t ]+");
		try {
			// save the model
			if (token.length == 1 && token[0].equalsIgnoreCase("save")) {
				doSaveAs();
				return true;
			}
			if (token.length > 2) {

				if (!token[0].equalsIgnoreCase("turtle")) {
					throw new TurtleCmdParserException("Turtle commands shall begin with turtle keyword.");
				}

				String turtleName = token[1];
				// search turtle
				Turtle turtle = null;

				// TODO Find the turtle named turtleName and returns it in
				// turtle

				if (token.length > 2) {
					if (turtle == null && !token[2].equalsIgnoreCase("create")) {
						throw new TurtleCmdParserException("Turtle " + turtleName + " not found.");
					}

					switch (token[2]) {
					// Destroy the turtle
					case "destroy": {
						// TODO Implement this command
						return true;
					}
					// Create a new turtle
					case "create": {
						// TODO Implement this command
						return true;
					}
					// Change the orientation of the turtle (in degrees,
					// trigonometric direction)
					case "turn": {
						if (token.length > 3) {
							// TODO Implement this command
						} else {
							throw new TurtleCmdParserException("Missing angle at the end of turn command.");
						}
						return true;
					}
					// Move the turtle forward / backward
					case "go": {
						if (token.length > 4) {
							switch (token[3]) {
							case "forward": {
								// TODO Implement this command
								return true;
							}
							case "backward": {
								// TODO Implement this command
								return true;
							}
							default: {
								throw new TurtleCmdParserException(
										"go command shall be followed by forward <steps> or backward <steps> option.");
							}
							}
						}
						break;
					}
					// Change the color of the trace
					case "color": {
						if (token.length > 3) {
							switch (token[3]) {
							case "blue": {
								// TODO Implement this command
								return true;
							}
							case "black": {
								// TODO Implement this command
								return true;
							}
							case "yellow": {
								// TODO Implement this command
								return true;
							}
							case "green": {
								// TODO Implement this command
								return true;
							}
							case "red": {
								// TODO Implement this command
								return true;
							}
							default: {
								throw new TurtleCmdParserException(
										"color command shall be followed by black, blue, red, green or yellow.");
							}
							}
						}
						break;
					}
					// Put / Raise the pen tip
					case "trace": {
						if (token.length > 3) {
							if (token[3].equals("on") || token[3].equals("off")) {
								// TODO Implement this command
								return true;
							} else if (token[3].equals("clear")) {
								// TODO Implement this command which clear all
								// drawn segments
								return true;
							}
						} else {
							throw new TurtleCmdParserException("Missing state (on/off) at the end of trace command.");
						}
						break;
					}
					// Syntax error
					default:
						throw new TurtleCmdParserException(
								"turtle <name> shall be followed by on of the following command: go, trace, turn, create, destroy, color, trace.");
					}
				} else {
					// Syntax error
					throw new TurtleCmdParserException(
							"turtle <name> shall be followed by on of the following command: go, trace, turn, create, destroy, color, trace.");
				}
			}
		} catch (TurtleCmdParserException ex) {
			// Print exceptions in the error log
			ex.printStackTrace();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.ui.parts.GraphicalEditor#init(org.eclipse.ui.IEditorSite,
	 * org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);

		ResourceSet resourceSet = new ResourceSetImpl();
		if (input instanceof IFileEditorInput) {
			// Retrieve file name...
			IFileEditorInput fileInput = (IFileEditorInput) input;
			IFile file = fileInput.getFile();
			turtleRes = resourceSet.createResource(URI.createURI(file.getLocationURI().toString()));
			// ...and load the file
			try {
				turtleRes.load(null);
				squareWorld = (SquareWorld) turtleRes.getContents().get(0);
			} catch (IOException e) {
				// TODO do something smarter.
				e.printStackTrace();
				turtleRes = null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#initializeGraphicalViewer()
	 */
	@Override
	protected void initializeGraphicalViewer() {
		getGraphicalViewer().setContents(squareWorld);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#configureGraphicalViewer()
	 */
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer().setRootEditPart(new ScalableRootEditPart());
		getGraphicalViewer().setEditPartFactory(new TurtleEditPartFactory());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.
	 * IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		saveOptions.put(Resource.OPTION_LINE_DELIMITER, Resource.OPTION_LINE_DELIMITER_UNSPECIFIED);

		try {
			squareWorld.eResource().save(saveOptions);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SquareWorld getSquareWorld() {
		return squareWorld;
	}
}
