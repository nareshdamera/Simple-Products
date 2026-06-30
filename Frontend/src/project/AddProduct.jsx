import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function AddProduct(){
    const navigate=useNavigate();
    const [product, setProduct] = useState({
    productid: "",
    name: "",
    category: "",
    price: "",
    available: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;

    setProduct((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem("token");

    try {
      const response = await fetch("http://localhost:9091/product/addproduct", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({
          productid: Number(product.productid),
          name: product.name,
          category: product.category,
          price: Number(product.price),
          available: Number(product.available),
        }),
      });

      if (response.ok) {
        alert("Product Added Successfully!");

        setProduct({
          productid: "",
          name: "",
          category: "",
          price: "",
          available: "",
        });
        navigate("/fetchallproducts")

      } else {
        alert("Failed to add product");
      }
    } catch (error) {
      console.error(error);
      alert("Error connecting to server");
    }
  };

  return (
    <div
      style={{
        width: "400px",
        margin: "40px auto",
        padding: "20px",
        border: "1px solid #ccc",
        borderRadius: "8px",
        boxShadow: "0 0 10px rgba(0,0,0,0.2)",
      }}
    >
      <h2 style={{ textAlign: "center" }}>Add Product</h2>

      <form onSubmit={handleSubmit}>

        <label>Product ID</label>
        <input
          type="number"
          name="productid"
          value={product.productid}
          onChange={handleChange}
          required
          style={inputStyle}
        />

        <label>Product Name</label>
        <input
          type="text"
          name="name"
          value={product.name}
          onChange={handleChange}
          required
          style={inputStyle}
        />

        <label>Category</label>
        <input
          type="text"
          name="category"
          value={product.category}
          onChange={handleChange}
          required
          style={inputStyle}
        />

        <label>Price</label>
        <input
          type="number"
          name="price"
          value={product.price}
          onChange={handleChange}
          required
          style={inputStyle}
        />

        <label>Available Quantity</label>
        <input
          type="number"
          name="available"
          value={product.available}
          onChange={handleChange}
          required
          style={inputStyle}
        />

        <button
          type="submit"
          style={{
            width: "100%",
            padding: "10px",
            marginTop: "15px",
            backgroundColor: "#1976d2",
            color: "white",
            border: "none",
            cursor: "pointer",
            borderRadius: "5px",
            fontSize: "16px",
          }}
        >
          Submit
        </button>

      </form>
    </div>
  );
}

const inputStyle = {
  width: "100%",
  padding: "8px",
  marginTop: "5px",
  marginBottom: "15px",
  boxSizing: "border-box",
};