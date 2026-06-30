import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function FetchCategory() {
    const navigate = useNavigate();

    const [pcategory, setPcategory] = useState("");
    const [message, setMessage] = useState("");
    const [product, setProduct] = useState(null);

    const fetchByCategory = async () => {
        const token = localStorage.getItem("token");

        try {
            if (!token) {
                setMessage("Enter Credentials");
                navigate("/");
                return;
            }

            const response = await fetch(
                `http://localhost:9091/product/getbycategory/${pcategory}`,
                {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": `Bearer ${token}`
                    }
                }
            );
            
            if (response.status === 404) {
                setMessage("No Product found");
                setProduct(null);
            }

            else if (response.ok) {
                const data = await response.json();

                if (data) {
                    setProduct(data); 
                    setMessage("");
                } else {
                    setProduct(null);
                    setMessage("No User found");
                }
            } else {
                setMessage("Something went wrong");
            }
        } catch (error) {
            setMessage("Server down");   
        }
    };

    return (
        <>
            <h3>Fetch product By Category</h3>

            <input
                type="text"
                placeholder="Enter category"
                value={pcategory}
                onChange={(e) => setPcategory(e.target.value)}
            />
            <button onClick={fetchByCategory}>Search</button>

            {message && <p>{message}</p>}

            {product && 
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
                        
                        {
                            product.map((p,index)=>(
                                <tr>
                                    <td>{p.productid}</td>
                                    <td>{p.name}</td>
                                    <td>{p.category}</td>
                                    <td>{p.price}</td>
                                    <td>{p.available}</td>
                                </tr>
                            ))
                        }
                        
                    </tbody>
                </table>
            }
        </>
    );
}