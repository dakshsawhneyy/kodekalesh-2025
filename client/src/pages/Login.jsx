import { useState } from "react";
const Login = () => {
    const [isLogin, setIsLogin] = useState(true);
    
  return (
        <div className="min-h-screen flex items-center justify-center bg-linear-to-br from-[#0d0d0f] via-[#1b1b1e] to-[#0f0f10] px-4">
      
      <div className="w-full max-w-md backdrop-blur-2xl bg-white/5 border border-white/10 p-10 rounded-3xl shadow-2xl transition-all">
        
        {/* Heading */}
        <h2 className="text-3xl font-semibold text-white text-center mb-8 tracking-wide">
          {isLogin ? "Welcome Back" : "Create Account"}
        </h2>

        {/* Toggle Buttons */}
        <div className="flex mb-6 bg-white/10 rounded-xl p-1">
          <button
            onClick={() => setIsLogin(true)}
            className={`w-1/2 py-2 rounded-xl font-medium transition ${
              isLogin
                ? "bg-white/20 text-white"
                : "text-white/60 hover:text-white"
            }`}
          >
            Login
          </button>

          <button
            onClick={() => setIsLogin(false)}
            className={`w-1/2 py-2 rounded-xl font-medium transition ${
              !isLogin
                ? "bg-white/20 text-white"
                : "text-white/60 hover:text-white"
            }`}
          >
            Register
          </button>
        </div>

        {/* Form */}
        <div className="space-y-4">

          {/* Register Name Field */}
          {!isLogin && (
            <input
              type="text"
              placeholder="Full Name"
              className="w-full p-3 rounded-xl bg-white/10 text-white border border-white/20 placeholder-white/60 focus:border-purple-400 focus:outline-none transition"
            />
          )}

          {/* Email */}
          <input
            type="email"
            placeholder="Email"
            className="w-full p-3 rounded-xl bg-white/10 text-white border border-white/20 placeholder-white/60 focus:border-purple-400 focus:outline-none transition"
          />

          {/* Password */}
          <input
            type="password"
            placeholder="Password"
            className="w-full p-3 rounded-xl bg-white/10 text-white border border-white/20 placeholder-white/60 focus:border-purple-400 focus:outline-none transition"
          />

          {/* Submit */}
          <button className="w-full bg-linear-to-r from-purple-600 to-pink-600 text-white py-3 rounded-xl font-semibold shadow-lg hover:opacity-90 transition">
            {isLogin ? "Login" : "Register"}
          </button>

        </div>

        {/* Bottom Text */}
        <p className="text-center text-white/50 text-sm mt-6">
          By continuing, you agree to our Terms & Privacy Policy
        </p>

      </div>
    </div>
  )
}

export default Login
