import { Component } from "react";
 
class InsertCustomerc extends Component
{
constructor()
{
    super();
    this.state={
        name:'',
        gender:'',
        email:'',
        pancard:'',
        dateofbirth:'',
        dateofregistration:'',
        message:''
 
    }
}
addCustomer=async ()=>
 
    {
        const token=localStorage.getItem("token")
   
   
   
        if(!token)
        {
        naviagate("/")
        return;
       
        }
        else
        {
        const cust=
        {
            name:this.state.name,
            gender:this.state.gender,
            email:this.state.email,
            pancard:this.state.pancard,
            dateofregistration:this.state.dateofregistration,
            dateofbirth:this.state.dateofbirth
        }
           
        alert(JSON.stringify(cust))
           
            const response=await fetch("http://localhost:9091/customer/add",{
                method:"POST",
                 headers:
                {
                "Content-Type":"application/json",
                "Authorization":"Bearer "+ token
                },
                body:JSON.stringify(cust)
 
            });
            if(response.status==400)
            {
              const data=await response.text();
                alert(data);
                this.setState({message:data})
                       }
             if(response.status==401)
            {
              const data=await response.json();
                alert(data.message);
                Navigate("/Unauthorise")
            }
            if(response.ok)
            {
             const data=await response.text()
           
                this.setState({message:data})
            }
 
        }
        }
 
 
   
 
 
 
 
 
 
render(){
return(<>
<table>
<tbody>
<tr><td>Name</td><td><input type="text" onChange={(e)=>this.setState({name:e.target.value})}/>   </td></tr>
<tr><td>Gender</td><td><input type="text" onChange={(e)=>this.setState({gender:e.target.value})}/>   </td></tr>
<tr><td>Email</td><td><input type="text" onChange={(e)=>this.setState({email:e.target.value})}/>   </td></tr>
<tr><td>Pancard</td><td><input type="text" onChange={(e)=>this.setState({pancard:e.target.value})}/>   </td></tr>
<tr><td>Date of Registration</td><td><input type="date" onChange={(e)=>this.setState({dateofregistration:e.target.value})}/>   </td></tr>
<tr><td>Date of Birth</td><td><input type="date" onChange={(e)=>this.setState({dateofbirth:e.target.value})}/>   </td></tr>
<tr><td><button onClick={(e)=>{e.preventDefault();this.addCustomer();}}>Click</button></td></tr>
</tbody>
</table>
<h3>{this.state.message}</h3>
</>);
}
 
   
}
 