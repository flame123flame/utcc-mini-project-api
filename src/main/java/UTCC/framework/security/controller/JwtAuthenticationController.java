package UTCC.framework.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UTCC.framework.security.authorization.JwtTokenUtil;
import UTCC.framework.security.service.JwtUserDetailsService;
import UTCC.framework.security.vo.JwtRequest;
import UTCC.framework.security.vo.JwtResponse;
import UTCC.project.user.model.FwUser;
import UTCC.project.user.repo.FwUserRepo;

@RestController
@CrossOrigin
@RequestMapping(value = "token/")
public class JwtAuthenticationController {
	
	@Autowired
	private FwUserRepo fwUserRepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;


	@PostMapping("authenticate")
	public ResponseEntity<JwtResponse> createAuthenticationToken(HttpServletRequest httpServletRequest, @RequestBody JwtRequest authenticationRequest) throws Exception {
		
		FwUser user = fwUserRepo.findByUsername(authenticationRequest.getUsername());
		if(user == null) {
	     	throw new Exception("INVALID_USER");
	    }
	    String client = getClientBrowser(httpServletRequest);
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		JwtResponse res = userDetailsService.setResponse(token, authenticationRequest.getUsername());
		if(!res.getPlatform().equals(client)) {
			throw new Exception("INVALID_USER");
		}
		return ResponseEntity.ok(res);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
  
    public static String getClientBrowser(HttpServletRequest request) {
        final String browserDetails = request.getHeader("User-Agent");
        final String user = browserDetails.toLowerCase();
        String[] webBrowsers = {"msie", "safari", "opr", "opera", "chrome", "mozilla/7.0", "netscape6",
                "mozilla/4.7", "mozilla/4.78", "mozilla/4.08", "mozilla/3", "postmanruntime", "firefox", "rv"};
        boolean isWebBrowser = false;
        for (String browser : webBrowsers) {
            if (user.contains(browser)) {
                isWebBrowser = true;
                break;
            }
        }
        return isWebBrowser ? "WEBSITE" : "APPLICATION";
    }
}
