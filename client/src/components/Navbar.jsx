import React from "react";
import { Link, useNavigate } from "react-router-dom";

const Navbar = () => {
  const navigate = useNavigate();
  return (
    <nav className="w-full fixed top-0 z-50 bg-linear-to-br from-[#0d0d0f]/80 via-[#1b1b1e]/80 to-[#0f0f10]/80 backdrop-blur-xl ">
      <div className="max-w-7xl mx-auto px-6 py-4 flex items-center justify-between">

        {/* Brand */}
        <h1 className="text-2xl font-semibold tracking-wide bg-linear-to-r from-purple-400 to-pink-400 bg-clip-text text-transparent">
          cGen
        </h1>

        {/* Links */}
        {
          localStorage.getItem('token') ?
            <div className="hidden md:flex space-x-8">
                     
                     <Link to="/text" className="text-white/70 hover:text-white transition text-sm font-medium">
                       GenText
                     </Link>
                     <Link to="/write" className="text-white/70 hover:text-white transition text-sm font-medium">
                       ReWrite
                     </Link>
                     <Link to="/img" className="text-white/70 hover:text-white transition text-sm font-medium">
                       GenImage
                     </Link>
                   </div>
             :
             <p className="text-white ">Log in to access our features</p>      
        }
       

        {/* Login Button */}
        {
          localStorage.getItem('token') ? 
           <button
          onClick={()=>{localStorage.removeItem('token');navigate('/login') }}
          className="px-5 py-2 rounded-xl bg-linear-to-r from-purple-600 to-pink-600 text-white font-semibold text-sm shadow-lg hover:opacity-90 transition"
        >
          Logout
        </button>
  :
           <Link
          to="/login"
          className="px-5 py-2 rounded-xl bg-linear-to-r from-purple-600 to-pink-600 text-white font-semibold text-sm shadow-lg hover:opacity-90 transition"
        >
          Login
        </Link>
        }
       

      </div>
    </nav>
  );
};

export default Navbar;
