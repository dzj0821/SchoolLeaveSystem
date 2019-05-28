package pers.dzj0821.SchoolLeaveSystem.listener;

import java.security.KeyPair;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import pers.dzj0821.SchoolLeaveSystem.Messages;
import pers.dzj0821.SchoolLeaveSystem.util.RSAUtil;

@WebListener
public class RSAKeySessionListener implements HttpSessionListener {

	/*每次创建一个session时，都会创建一个RSA密钥*/
	/*currentTimeMillis() ：获得当前的时间*/
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		KeyPair keyPair = RSAUtil.genKeyPair();
		HttpSession session = event.getSession();
		session.setAttribute(Messages.getString("RSAKeyPairSessionName"), keyPair); //$NON-NLS-1$
		session.setAttribute(Messages.getString("RSACreateTimestampSessionName"), System.currentTimeMillis()); //$NON-NLS-1$
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
	}

}
