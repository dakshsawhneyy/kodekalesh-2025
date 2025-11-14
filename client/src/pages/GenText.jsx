import React, { useState } from "react";
import { motion } from "framer-motion";
import axios from "axios";

const GenText = () => {
  const [form, setForm] = useState({
    category: "",
    topic: "",
    tone: "",
    language: "",
    other_details: "",
  });

  const [output, setOutput] = useState(null);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "https://rv6p8wji3k.execute-api.ap-south-1.amazonaws.com/kode-kalesh/generateText",
        form
      );
      let data = response.data;

// Step 1: If it's enclosed in backticks or code block formatting, remove them
if (typeof data === "string") {
  data = data.replace(/```/g, "").trim();
}

// Step 2: Try converting from string → actual JSON object
try {
  data = JSON.parse(data);
} catch (e) {
  console.log("Not JSON, returning raw string instead:", data);
}

// Now data is REAL JSON (if convertible)
setOutput(data);
    } catch (error) {
      console.log(error.message);
    }
  };

  return (
    <div className="min-h-screen w-full flex justify-center items-start p-10 bg-linear-to-br from-[#0f0f1f] via-[#161628] to-[#0c0c18] relative">
      <div className="w-full max-w-4xl space-y-10">
        {/* BACK BUTTON */}
        <button
          onClick={() => (window.location.href = "/")}
          className="absolute top-6 left-6 p-3 rounded-xl bg-white/10 text-white border border-white/20 hover:bg-white/20 backdrop-blur-xl shadow-md"
        >
          ←
        </button>

        <h1 className="text-4xl font-semibold text-white text-center">
          Generate Text
        </h1>

        {/* INPUT GRID */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          {/* CATEGORY */}
          <motion.div whileHover={{ scale: 1.02 }} className="backdrop-blur-xl bg-white/10 p-5 border border-white/20 rounded-2xl shadow-lg">
            <label className="block mb-2 text-sm text-gray-300">Category</label>
            <select
              name="category"
              value={form.category}
              onChange={handleChange}
              className="w-full p-3 rounded-xl bg-[#1b1b2d] text-white border border-white/20"
            >
              <option value="">Select Category</option>
              <option value="education">Education</option>
              <option value="marketing">Marketing</option>
              <option value="social_media">Social Media</option>
              <option value="blog">Blog</option>
              <option value="public_outreach">Public Outreach</option>
            </select>
          </motion.div>

          {/* TONE */}
          <motion.div whileHover={{ scale: 1.02 }} className="backdrop-blur-xl bg-white/10 p-5 border border-white/20 rounded-2xl shadow-lg">
            <label className="block mb-2 text-sm text-gray-300">Tone</label>
            <select
              name="tone"
              value={form.tone}
              onChange={handleChange}
              className="w-full p-3 rounded-xl bg-[#1b1b2d] text-white border border-white/20"
            >
              <option value="">Select Tone</option>
              <option value="Formal">Formal</option>
              <option value="Casual">Casual</option>
              <option value="Professional">Professional</option>
              <option value="Friendly">Friendly</option>
              <option value="Funny">Funny</option>
            </select>
          </motion.div>

          {/* LANGUAGE */}
          <motion.div whileHover={{ scale: 1.02 }} className="backdrop-blur-xl bg-white/10 p-5 border border-white/20 rounded-2xl shadow-lg">
            <label className="block mb-2 text-sm text-gray-300">Language</label>
            <select
              name="language"
              value={form.language}
              onChange={handleChange}
              className="w-full p-3 rounded-xl bg-[#1b1b2d] text-white border border-white/20"
            >
              <option value="">Select Language</option>
              <option value="English">English</option>
              <option value="Hindi">Hindi</option>
              <option value="Hinglish">Hinglish</option>
            </select>
          </motion.div>
        </div>

        {/* TOPIC + DETAILS */}
        <motion.div whileHover={{ scale: 1.01 }} className="backdrop-blur-xl bg-white/10 p-6 border border-white/20 rounded-2xl shadow-lg">
          <label className="block mb-2 text-sm text-gray-300">Topic</label>
          <input
            type="text"
            name="topic"
            value={form.topic}
            onChange={handleChange}
            placeholder="Enter topic..."
            className="w-full p-3 mb-4 rounded-xl bg-[#1b1b2d] text-white border border-white/20"
          />

          <label className="block mb-2 text-sm text-gray-300">Other Details</label>
          <textarea
            name="other_details"
            rows={4}
            value={form.other_details}
            onChange={handleChange}
            placeholder="Additional context..."
            className="w-full p-3 rounded-xl bg-[#1b1b2d] text-white border border-white/20"
          />
        </motion.div>

        {/* SUBMIT */}
        <motion.button
          whileTap={{ scale: 0.97 }}
          whileHover={{ scale: 1.03 }}
          onClick={handleSubmit}
          className="w-full py-4 rounded-xl bg-blue-500/80 hover:bg-blue-600 text-white text-lg"
        >
          Generate
        </motion.button>

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
  );
};

export default GenText;