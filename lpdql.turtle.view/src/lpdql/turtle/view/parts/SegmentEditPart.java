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
import org.eclipse.draw2d.PolylineShape;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.swt.SWT;

import turtle.Segment;

/**
 * The class SegmentEditPart is the controller of Segment in this Model-View-Controller architecture
 *
 */
public class SegmentEditPart extends NotifiedEditPart implements
		EditPart {

	@Override
	protected IFigure createFigure() {
		// The trace made of all segments for a Turtle is drawn as a polyline
		return new PolylineShape();
	}

	@Override
	protected void createEditPolicies() {
		// Nothing to do
	}

	@Override
	protected void refreshVisuals() {
		PolylineShape figure = (PolylineShape) getFigure();
		Segment segment = (Segment) getModel();
		SquareWorldEditPart parent = (SquareWorldEditPart) getParent();

		Rectangle layout = new Rectangle(0, 0, parent.getContentPane()
				.getBounds().width, parent.getContentPane().getBounds().width);
		parent.setLayoutConstraint(this, figure, layout);

		figure.removeAll();
		// TODO Complete this command
		// TODO Add the origin point
		figure.addPoint(new Point(/* X */, /* Y */));
		// TODO Add the destination point
		figure.addPoint(new Point(/* X */, /* Y */));
		// TODO Set the appropriate color
		figure.setForegroundColor(/* Color here */);
		figure.setLineWidth(3);
		figure.setLineCap(SWT.CAP_ROUND);
	}
}
