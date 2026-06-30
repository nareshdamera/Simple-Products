import { Component } from "react";

class FetchCustomerc extends Component {
  constructor() {
    super();
    this.state = {
      products: [],
      error: [],
    };
  }

  fetchProducts = async () => {
    const token = localStorage.getItem("token");
    if (!token) {
      alert("Login using credentials");
      return;
    }

    try {
      const response = await fetch("http://localhost:9091/product/getall", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });
      if(!response.ok){
        const errorMessage=await response.text()
        throw new Error(errorMessage)
      }
      else{
        const data=await response.json()
        this.setState({products:data})
      }
    } catch (err) {
        this.setState({
            error:err.message
        })
    }
  };
  componentDidMount() {
    this.fetchProducts(); //  componentDidMount is same as useEffect
  }
  render() {
    return (
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
            {this.state.products.map((p, index) => (
              <tr key={index}>
                <td>{p.productid}</td>
                <td>{p.name}</td>
                <td>{p.category}</td>
                <td>{p.price}</td>
                <td>{p.available}</td>
              </tr>
            ))}
          </tbody>
          
        </table>
      </>
    );
  }
}
export default FetchCustomerc;
