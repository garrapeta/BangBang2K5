/*
 * Created on 22-may-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package GUI;

import java.awt.Component;
import java.awt.ContainerOrderFocusTraversalPolicy;

/**
 * @author kn0t
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LoginGUIContainer extends ContainerOrderFocusTraversalPolicy {

	/**
	 * 
	 */
	protected boolean accept(Component component){
		String clase = component.getClass().toString();
		if ( clase.equals("class javax.swing.JLabel") || clase.equals("class GUI.LoginGUI") 
				|| clase.equals("class GUI.LoginGUI") || clase .equals("class javax.swing.JRootPane")
				|| clase.equals("class javax.swing.JLayeredPane") 
				|| clase.equals("class javax.swing.JPanel") )
			return false;
		else return true;
	}

}
