import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'



import {BrowserRouter,Route,Routes} from 'react-router-dom'
import Login from './project/Login'
import Navbar from './components/Navbar'
import FetchProducts from './project/FetchProducts'
import FetchCategory from './project/FetchCategory'
import Product from './project/Product'
import FetchCustomerc from './project/FetchCustomerc'
import AddProduct from './project/AddProduct'
import AddUser from './project/AddUser'


function App() {
  const[role,setRole]=useState(localStorage.getItem("role")||"")
  return (
    <>
      <BrowserRouter>
      <Navbar role={role} setRole={setRole}/>
      <Routes>
          <Route path="/" element={<Login setRole={setRole}/>} />
          <Route path="/fetchallproducts" element={<FetchProducts/>}/>
          <Route path="/fetchbycategory" element={<FetchCategory/>}/>
          <Route path="/product" element={<Product/>}/>
          <Route path="/fetchproductsc" element={<FetchCustomerc/>}/>
          <Route path="/addproduct" element={<AddProduct/>}/>
          <Route path="/adduser" element={<AddUser/>}/>          
      </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
