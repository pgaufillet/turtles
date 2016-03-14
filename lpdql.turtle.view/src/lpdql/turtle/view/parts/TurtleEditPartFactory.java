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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import turtle.Segment;
import turtle.SquareWorld;
import turtle.Turtle;

/**
 * The class TurtleEditPartFactory is responsible for creating the appropriate
 * EditPart for each object of the model.
 *
 */
public class TurtleEditPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;

		// Depending on the type of the object, create the corresponding
		// EditPart
		if (model instanceof SquareWorld) {
			part = new SquareWorldEditPart();
		} else if (model instanceof Turtle) {
			part = new TurtleEditPart();
		} else if (model instanceof Segment) {
			part = new SegmentEditPart();
		}

		// Additionally, register a listener responsible for updating
		// automatically the figure each time the object change.
		if (part != null) {
			part.setModel(model);
			if (part instanceof NotifiedEditPart) {
				NotifiedEditPart nPart = (NotifiedEditPart) part;
				nPart.hookAdapter();
			}
		}

		return part;
	}

}
