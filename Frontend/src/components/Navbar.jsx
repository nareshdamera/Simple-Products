// import '../MyStyles/styles.css'
// import logo from "../images/Logo.png"
import { useNavigate } from "react-router-dom";
 
export default function Navbar({role,setRole}) {
const navigate = useNavigate();
 const logoutHandle=(e)=>{
    e.preventDefault();
    //try without qoutes
    localStorage.removeItem("token")
    localStorage.removeItem("role")
    setRole("")
    navigate("/")
 }
 
  return (
    <>
      {/* <div className="top">
        <img src={logo} style={{ width: "100%", height: "100%" }} />
      </div> */}
 
        {role==="ADMIN"?(
            <div className="menu">
                <ul>
                    <li><a href="/" onClick={logoutHandle}>Logout</a></li>
                    <li><a href="/fetchallproducts">Show Products</a></li>
                    <li><a href="/fetchbycategory">Find Products by Category</a></li>
                    <li><a href="/fetchproductsc">Get Products C</a></li>
                    {/* <li><a href="/insertcustomer">Add Employee</a></li>
                    <li><a href="/updatecustomer">Update Employee</a></li>
                    <li><a href="/deletecustomer">Delete Employee</a></li> */}
                </ul>
            </div>

        ):role==="USER"?(
            <div className="menu">
                <ul>
                    <li><a href="/" onClick={logoutHandle}>Logout</a></li>
                    <li><a href="/fetchallproducts">Show Products</a></li>
                    <li><a href="/fetchbycategory">Find Products by Category</a></li>
                    <li><a href="/fetchproductsc">Get Products C</a></li>
                </ul>
            </div>
        ):(
            <div className="menu">
                {/* <ul>
                    <li><a href="/">Login</a></li>
                    <li><a href="/About">About us</a></li>
                    <li><a href="/Services">Our Services</a></li>
                </ul> */}
            </div>
        )}
        
 
     
    </>
  );
}