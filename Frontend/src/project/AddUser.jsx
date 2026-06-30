import React, { useState } from "react";

const AddUser = () => {
  const [user, setUser] = useState({
    email: "",
    password: "",
    role: "",
  });

  const handleChange = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:9091/auth/save", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(user),
      });

      if (response.ok) {
        alert("User added successfully!");

        setUser({
          email: "",
          password: "",
          role: "",
        });
      } else {
        alert("Failed to add user.");
      }
    } catch (error) {
      console.error(error);
      alert("Unable to connect to the server.");
    }
  };

  return (
    <div
      style={{
        width: "400px",
        margin: "40px auto",
        padding: "20px",
        border: "1px solid #ccc",
        borderRadius: "10px",
        boxShadow: "0 2px 10px rgba(0,0,0,0.2)",
      }}
    >
      <h2 style={{ textAlign: "center" }}>Add New User</h2>

      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: "15px" }}>
          <label>Email</label>
          <input
            type="email"
            name="email"
            value={user.email}
            onChange={handleChange}
            required
            style={inputStyle}
          />
        </div>

        <div style={{ marginBottom: "15px" }}>
          <label>Password</label>
          <input
            type="password"
            name="password"
            value={user.password}
            onChange={handleChange}
            required
            style={inputStyle}
          />
        </div>

        <div style={{ marginBottom: "15px" }}>
          <label>Role</label>
          <select
            name="role"
            value={user.role}
            onChange={handleChange}
            required
            style={inputStyle}
          >
            <option value="">-- Select Role --</option>
            <option value="ADMIN">ADMIN</option>
            <option value="USER">USER</option>
          </select>
        </div>

        <button
          type="submit"
          style={{
            width: "100%",
            padding: "10px",
            backgroundColor: "#007bff",
            color: "#fff",
            border: "none",
            borderRadius: "5px",
            cursor: "pointer",
            fontSize: "16px",
          }}
        >
          Add User
        </button>
      </form>
    </div>
  );
};

const inputStyle = {
  width: "100%",
  padding: "8px",
  marginTop: "5px",
  boxSizing: "border-box",
};

export default AddUser;