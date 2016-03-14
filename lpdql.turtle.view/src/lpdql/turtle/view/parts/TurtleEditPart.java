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
package lpdql.turtle.view.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import turtle.Turtle;

/**
 * The class TurtleEditPart is the controller of Turtle in this Model-View-Controller architecture
 *
 */
public class TurtleEditPart extends NotifiedEditPart {

	@Override
	protected IFigure createFigure() {
		return new TurtleFigure();
	}

	@Override
	protected void createEditPolicies() {
		// Nothing to do for this simple viewer
	}

	@Override
	protected void refreshVisuals() {
		TurtleFigure figure = (TurtleFigure) getFigure();
		Turtle turtle = (Turtle) getModel();
		SquareWorldEditPart parent = (SquareWorldEditPart) getParent();

		// Regenerate the label in the case the name of the turtle has changed.
		// TODO Complete this command
		figure.getLabel().setText(/* Retrieve turtle label */);
		
		// Regenerate the figure in case the turtle has moved
		Rectangle layout = new Rectangle(/* Retrieve turtle X coordinate */ - 36,
				/* Retrieve turtle Y coordinate */ - 36, 72, 72);
		parent.setLayoutConstraint(this, figure, layout);

		// Transmit the new orientation of the turtle
		figure.setOrientation(/* Retrieve turtle orientation */);
	}
}
