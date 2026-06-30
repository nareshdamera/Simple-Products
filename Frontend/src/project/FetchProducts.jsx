import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
export default function FetchProducts(){
    const navigate=useNavigate()
    const[product,setProduct]=useState([])
    const[error,setError]=useState("")

    const fetchall=async ()=>{
        const token=localStorage.getItem("token")
        try{
            if(!token){
                alert("Enter Credentials")
                navigate("/")
                return
            }
            else{
                const response= await fetch("http://localhost:9091/product/getall",{
                    method:"GET",
                        headers:{
                            "Content-Type":"application/json",
                            "Authorization":`Bearer ${token}`
                        }
                })
                if(response.ok){
                    const data=await response.json()
                    setProduct(data)
                }else{
                    setError("Something went wrong")
                }
            }
        }catch(error){
            setError("Server Down")
        }
    }
    
    useEffect(() => {
        fetchall();
    },[]);

    return(
        <>
            <h2>product data</h2>
            {/* <button onClick={fetchall}>View Emp data</button> */}

            
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th>Available</th>
                    </tr>
                </thead>
                <tbody>
                    {product.map((p,index) => (
                        <tr key={index}>
                            <td>{p.productid}</td>
                            <td>{p.name}</td>
                            <td>{p.category}</td>
                            <td>{p.price}</td>
                            <td>{p.available}</td>
                        </tr>
                    ))}
                </tbody>
                {error}
            </table>
        </>
    )
}