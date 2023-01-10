package com.wook.prj01.web.member.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/* Spring Security 로그인을 위한 UserDetails VO 객체 */
public class UserDetailsVo implements UserDetails {

	// 안만들어도 상관없지만 Warning이 발생함
	private static final long serialVersionUID = 1L;
	
	private String username; // ID(security에서는 ID를 'username'이라고 표현)
	private String password; // PW
	private List<GrantedAuthority> authorities; //권한
	
	//권한 객체 생성 : new SimpleGrantedAuthority("권한명")
	public void setAuthorities(List<String> authList) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (int i = 0; i < authList.size(); i++) {
			authorities.add(new SimpleGrantedAuthority(authList.get(i)));
		}
		this.authorities = authorities;
	}
		
	// setter getter
	public void setUsername(String username) {this.username = username;}
	public void setPassword(String password) {this.password = password;}
	@Override
	public String getUsername() {return username;}
	@Override
	public String getPassword() {return password;}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {return authorities;}
	

	// 계정이 만료 되지 않았는가?
	@Override
	public boolean isAccountNonExpired() {return false;}
	// 계정이 잠기지 않았는가?
	@Override
	public boolean isAccountNonLocked() {return false;}
	// 패스워드가 만료되지 않았는가?
	@Override
	public boolean isCredentialsNonExpired() {return false;}
	// 계정이 활성화 되었는가?ride
	@Override 
	public boolean isEnabled() {return false;}

}
