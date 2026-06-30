package com.restapidemo.restdemo.filter;

import java.io.IOException;
import org.hibernate.boot.model.convert.spi.ConverterAutoApplyHandler;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.restapidemo.restdemo.Models.DL.ServiceImpl.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final UserDetailsService userDetailsService;
	private final JWTService jwtService;
	
	public JwtAuthenticationFilter(UserDetailsService userDetailsService,JWTService jwtService)
	{
		this.jwtService=jwtService;
		this.userDetailsService=userDetailsService;
		
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token=null;
		String username=null;
		
		
		String authheader=request.getHeader("Authorization");
		
		
		System.out.println(authheader);
		if(authheader!=null && authheader.startsWith("Bearer ")) 
		{
			
			token=authheader.substring(7);
			username=jwtService.extractSubjectFromToken(token);
		}
		System.out.println(username);
		System.out.println(token);
		System.out.println("fileter   "+ jwtService.validateToken(token));
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails= userDetailsService.loadUserByUsername(username);
			System.out.println(jwtService.validateToken(token));
			if(jwtService.validateToken(token))
			{
			System.out.println("hhhhhhhhhhhhhhhhhhhh");
			System.out.println(userDetails.getAuthorities());

				UsernamePasswordAuthenticationToken authtoken=
						new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				System.out.println("first");
				authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				System.out.println("second");
				System.out.println(authtoken.getDetails());
				SecurityContextHolder.getContext().setAuthentication(authtoken);
				System.out.println("Third");				
			}
		}
		System.out.println("done");
		System.out.println(request.getRequestURL());
		System.err.println(response.getStatus());
		filterChain.doFilter(request, response);
		System.out.println("ddddd");
		
		//Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VydHlwZSI6IlVTRVIiLCJzdWIiOiJhYmNkQGdtYWlsLmNvbSIsImlhdCI6MTc4MTI0NDYzNCwiZXhwIjoxNzgxMjQ1MjM0fQ.AGV4LD1ZgqkyKRfYa-ugI6T92Cks4teDaK_qlvgeI0E
		
	}
}
