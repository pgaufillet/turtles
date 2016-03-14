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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

/**
 * The abstract class NotifiedEditPart automatically updates the figure on model change.
 *
 */
public abstract class NotifiedEditPart extends AbstractGraphicalEditPart {

	public void hookAdapter() {
		EObject eobject = (EObject) getModel();
		EContentAdapter adapter = new EContentAdapter() {
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				refresh();
			}
		};
		// Register our adapter
		eobject.eAdapters().add(adapter);
	}
}
