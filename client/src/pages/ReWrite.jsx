import React, { useState } from "react";
import { motion } from "framer-motion";
import axios from "axios";

const ReWrite = () => {
  const [form, setForm] = useState({
    text: "",
    platform: "",
  });

  const [output, setOutput] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(form);
    try {
        const response = await axios.post("https://rv6p8wji3k.execute-api.ap-south-1.amazonaws.com/kode-kalesh/rewriteForPlatform",form);
        console.log(response.data);
        setOutput(response.data);
    } catch (error) {
      console.log(error.message)
    }
  };
 return  localStorage.getItem('token') ? (
    <div className="min-h-screen  w-full flex justify-center items-start p-10 bg-linear-to-br from-[#0f0f1f] via-[#161628] to-[#0c0c18] relative">
      <div className="w-full max-w-4xl space-y-10">

        {/* Back Button */}
        <button
          onClick={() => window.location.href = "/"}
          className="absolute top-6 left-6 p-3 rounded-xl bg-white/10 text-white border border-white/20 hover:bg-white/20 transition-all backdrop-blur-xl shadow-md flex items-center justify-center"
        >
          <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2">
            <path strokeLinecap="round" strokeLinejoin="round" d="M15 19l-7-7 7-7" />
          </svg>
        </button>

        <h1 className="text-4xl font-semibold text-white tracking-wide text-center mb-4">Rewrite Text For Different Platforms</h1>

        {/* Input Section */}
        <motion.div whileHover={{ scale: 1.01 }} className="backdrop-blur-xl bg-white/10 p-6 border border-white/20 rounded-2xl shadow-lg space-y-4">
          <label className="block mb-2 text-sm text-gray-300">Enter Text to Rewrite</label>
          <textarea
            name="text"
            rows={5}
            placeholder="Paste your text here..."
            value={form.text}
            onChange={handleChange}
            className="w-full p-3 rounded-xl bg-[#1b1b2d] text-white border border-white/20 focus:outline-none focus:ring-2 focus:ring-blue-400"
          />


          {/* Platform */}
          <div>
            <label className="block mb-1 text-sm text-gray-300">Platform</label>
            <select
              name="platform"
              value={form.platform}
              onChange={handleChange}
              className="w-full p-3 rounded-xl bg-[#1b1b2d] text-white border border-white/20 focus:outline-none focus:ring-2 focus:ring-blue-400"
            >
              <option className="bg-[#1b1b2d]" value="" disabled>Select Platform</option>
              <option className="bg-[#1b1b2d]" value="instagram">Instagram</option>
              <option className="bg-[#1b1b2d]" value="linkedIn">LinkedIn</option>
              <option className="bg-[#1b1b2d]" value="twitter">Twitter</option>
              <option className="bg-[#1b1b2d]" value="youTube">YouTube</option>
            </select>
          </div>

          {/* Submit */}
          <motion.button
            whileTap={{ scale: 0.97 }}
            whileHover={{ scale: 1.03 }}
            onClick={handleSubmit}
            className="w-full py-3 rounded-xl bg-blue-500/80 hover:bg-blue-600 transition-all shadow-xl text-white text-lg font-semibold"
          >
            Rewrite 
          </motion.button>
        </motion.div>

        <button
  onClick={() => (window.location.href = "/feedback")}
  className="p-3 rounded-xl bg-white/10 text-white border border-white/20 hover:bg-white/20 backdrop-blur-xl shadow-md"
>
  Give Feedback
</button>

              {output && (
  <div className="mt-10 p-6 bg-white/10 border border-white/20 rounded-2xl text-white">
    {Object.keys(output).map((key) => (
      <div key={key} className="mb-6">
        <h2 className="text-2xl font-semibold capitalize mb-2">{key.replace(/_/g, ' ')}</h2>

        {Array.isArray(output[key]) ? (
          <ul className="list-disc pl-6">
            {output[key].map((item, idx) => (
              <li key={idx}>{item}</li>
            ))}
          </ul>
        ) : (
          <p className="text-gray-300">{output[key]}</p>
        )}
      </div>
    ))}
  </div>
)}


      </div>
    </div>
  )
  :
   (
   <div className="min-h-screen flex items-center justify-center bg-linear-to-br from-[#0d0d0f] via-[#1a1a1d] to-[#0e0e10] px-4">
  <div className="backdrop-blur-2xl bg-white/5 border border-white/10 px-10 py-12 rounded-3xl shadow-2xl text-center max-w-md w-full">
    
    <h1 className="text-3xl font-semibold text-white mb-4 tracking-wide">
      Please Login to Continue
    </h1>

    <p className="text-white/60 text-sm mb-8">
      Access your dashboard and start exploring powerful features.
    </p>

    <a
      href="/login"
      className="inline-block bg-linear-to-r from-purple-600 to-pink-600 text-white py-3 px-6 rounded-xl font-medium shadow-lg hover:opacity-90 transition cursor-pointer"
    >
      Go to Login
    </a>
  </div>
</div>
  )

}

export default ReWrite
