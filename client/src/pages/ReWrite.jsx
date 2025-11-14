import React, { useState } from "react";
import { motion } from "framer-motion";

const ReWrite = () => {
  const [form, setForm] = useState({
    text: "",
    tone: "",
    platform: "",
  });

  const [output, setOutput] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);

    // Mock AI response
    setTimeout(() => {
      setOutput(
        `Rewritten (${form.tone}, for ${form.platform}): \n\n` + form.text
      );
      setLoading(false);
    }, 1200);
  };
 return (
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

        <h1 className="text-4xl font-semibold text-white tracking-wide text-center mb-4">Rewrite Text</h1>

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

          {/* Tone */}
          <div>
            <label className="block mb-1 text-sm text-gray-300">Tone</label>
            <select
              name="tone"
              value={form.tone}
              onChange={handleChange}
              className="w-full p-3 rounded-xl bg-[#1b1b2d] text-white border border-white/20 focus:outline-none focus:ring-2 focus:ring-blue-400"
            >
              <option className="bg-[#1b1b2d]" value="" disabled>Select Tone</option>
              <option className="bg-[#1b1b2d]" value="Formal">Formal</option>
              <option className="bg-[#1b1b2d]" value="Casual">Casual</option>
              <option className="bg-[#1b1b2d]" value="Professional">Professional</option>
              <option className="bg-[#1b1b2d]" value="Friendly">Friendly</option>
              <option className="bg-[#1b1b2d]" value="Funny">Funny</option>
            </select>
          </div>

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

        {/* Output Section */}
        <motion.div animate={{ opacity: output ? 1 : 0 }} className="backdrop-blur-xl bg-white/10 p-6 border border-white/20 rounded-2xl shadow-lg text-white whitespace-pre-wrap min-h-[150px]">
          {loading ? (
            <p className="text-gray-300">Rewriting...</p>
          ) : (
            output || <p className="text-gray-400">Your rewritten text will appear here...</p>
          )}
        </motion.div>
      </div>
    </div>
  );
}

export default ReWrite
