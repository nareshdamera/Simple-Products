import { useState } from "react"
import { useNavigate } from "react-router-dom";

export default function Login({setRole}){
    const[username,setUsername]=useState("");
    const[password,setPassword]=useState("");
    const[message,setMessage]=useState("");
    const navigate=useNavigate();

    const handleLogin= async (e)=>{
        e.preventDefault(); 
        try{

            const response=await fetch("http://localhost:9091/auth/validate",
                {
                    method:"POST",
                    headers:{
                        "Content-Type":"application/json"
                    },
                    body:JSON.stringify({email:username,password:password})
                }
            );
            
            if(!response.ok){
                alert("Invalid username or password")
                setMessage("Invalid username and password");
                return;
            }else{
                const data= await response.json();
                localStorage.setItem("token",data.token)
                localStorage.setItem("role",data.role)
                setRole(data.role)
                if(data.role==="ADMIN"){
                    navigate("/product")
                }else{
                    navigate("/product")
                }
            }
        }catch(error){
            setMessage("Server down. Try after sometime.")
        }
        
    }
    function loginHandler(){

    }
    return(
        <>
            <form onSubmit={handleLogin} >

                <table>
                    <tbody>
                        <tr>
                            <td>Username</td>
                            <td><input type="text" onChange={(e)=>setUsername(e.target.value)} /></td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><input type="password" onChange={(e)=>setPassword(e.target.value)} /></td>
                        </tr>
                        <tr>
                            <td>
                                <button type="submit">Submit</button>
                            </td>
                            
                        </tr>
                       
                    </tbody>
                </table>
                {message}
            </form>
        </>
    )
}