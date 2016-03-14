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

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolygonShape;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transform;
import org.eclipse.swt.SWT;

/**
 * The class TurtleFigure is the View part of this Model-View-Controller architecture
 *
 */
public class TurtleFigure extends Figure {

	private PointList points;

	private PolygonShape triangle;

	private Ellipse pen;

	private Label label;

	private double orientation;

	public double getOrientation() {
		return orientation;
	}

	public void setOrientation(double orientation) {
		this.orientation = orientation;
	}

	public TurtleFigure() {
		setLayoutManager(new XYLayout());

		orientation = 0.0;

		// The figure is a triangle...
		points = new PointList();
		points.addPoint(11, 11);
		points.addPoint(60, 35);
		points.addPoint(11, 60);
		points.addPoint(11, 11);
		triangle = new PolygonShape();
		triangle.setPoints(points);
		triangle.setLineWidth(3);
		triangle.setLineJoin(SWT.JOIN_ROUND);
		add(triangle);

		// ... with a pen tip...
		pen = new Ellipse();
		add(pen);

		// ... with the name of the turtle as label.
		label = new Label();
		add(label);
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		// First paint the pen tip at the center of the figure, diameter 6
		Rectangle r = getBounds().getCopy();
		setConstraint(pen, new Rectangle(35, 35, 3, 3));
		
		// Now transform the basic triangle to match the orientation of the turtle
		Transform translation = new Transform();
		translation.setTranslation(-36, -36);
		Transform transform = new Transform();
		transform.setTranslation(36, 36);
		transform.setRotation(orientation);

		PointList rotatedPoints = new PointList();
		// Center on the triangle center
		rotatedPoints.addPoint(translation.getTransformed(points.getPoint(0)));
		rotatedPoints.addPoint(translation.getTransformed(points.getPoint(1)));
		rotatedPoints.addPoint(translation.getTransformed(points.getPoint(2)));
		rotatedPoints.addPoint(translation.getTransformed(points.getPoint(3)));

		// Then rotate accordingly to the orientation of the turtle and go back to the initial origin
		rotatedPoints.setPoint(
				transform.getTransformed(rotatedPoints.getPoint(0)), 0);
		rotatedPoints.setPoint(
				transform.getTransformed(rotatedPoints.getPoint(1)), 1);
		rotatedPoints.setPoint(
				transform.getTransformed(rotatedPoints.getPoint(2)), 2);
		rotatedPoints.setPoint(
				transform.getTransformed(rotatedPoints.getPoint(3)), 3);

		// Replace the actual figure by the result of this transformation
		triangle.setPoints(rotatedPoints);
		
		// ... And set the size of triangle and label for final representation
		setConstraint(triangle, new Rectangle(0, 0, r.width, r.height));
		setConstraint(label, new Rectangle(11, 16, 31, 31));

	}

	public Label getLabel() {
		return label;
	}
}
