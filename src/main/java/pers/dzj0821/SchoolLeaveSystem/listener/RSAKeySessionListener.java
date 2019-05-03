package pers.dzj0821.SchoolLeaveSystem.listener;

import java.security.KeyPair;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import pers.dzj0821.SchoolLeaveSystem.Messages;
import pers.dzj0821.SchoolLeaveSystem.util.RSAUtil;

@WebListener
public class RSAKeySessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		KeyPair keyPair = RSAUtil.genKeyPair();
		event.getSession().setAttribute(Messages.getString("RSAKeyPairSessionName"), keyPair); //$NON-NLS-1$
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
	}

}