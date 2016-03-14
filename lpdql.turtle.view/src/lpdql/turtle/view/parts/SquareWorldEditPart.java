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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.emf.ecore.EObject;

import turtle.Segment;
import turtle.SquareWorld;
import turtle.Turtle;

/**
 * The class SquareWorldEditPart is the controller of SquareWorld in this
 * Model-View-Controller architecture
 *
 */
public class SquareWorldEditPart extends NotifiedEditPart {

	@Override
	protected IFigure createFigure() {
		// For SquareWorld, the figure is a simple canvas
		FreeformLayer layer = new FreeformLayer();
		layer.setLayoutManager(new FreeformLayout());
		layer.setBorder(new LineBorder(1));
		return layer;
	}

	@Override
	protected void createEditPolicies() {
		// Nothing to do for this simple viewer

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
	 */
	@Override
	protected List<EObject> getModelChildren() {
		List<EObject> retVal = new ArrayList<EObject>();
		SquareWorld world = (SquareWorld) getModel();

		// The children that need to be taken into account are the Turtles of
		// this SquareWorld
		// And their drawn segments
		retVal.addAll(world.getTurtles());
		for (Turtle t : world.getTurtles()) {
			retVal.addAll(t.getSegments());
		}
		return retVal;
	}

}
