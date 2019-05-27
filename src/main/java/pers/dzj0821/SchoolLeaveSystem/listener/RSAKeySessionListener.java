package pers.dzj0821.SchoolLeaveSystem.listener;

import java.security.KeyPair;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import pers.dzj0821.SchoolLeaveSystem.adapter.HttpSessionAdapter;
import pers.dzj0821.SchoolLeaveSystem.util.RSAUtil;
/**
 * session创建时的监听器，用于给session分配RSA密钥对和时间戳（用于验证密钥对是否过期）
 * @author dzj0821
 *
 */
@WebListener
public class RSAKeySessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		//生成一个RSA密钥对
		KeyPair keyPair = RSAUtil.genKeyPair();
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(event.getSession());
		sessionAdapter.setRSAKeyPair(keyPair);
		sessionAdapter.setRSACreateTimestamp(System.currentTimeMillis());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
	}

}
