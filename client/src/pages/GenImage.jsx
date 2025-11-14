import React, { useState } from "react";
import { motion } from "framer-motion";
import axios from "axios";

const GenImage = () => {
  const [form, setForm] = useState({
    category: "",
    topic: "",
    purpose: "",
  });

  const [image, setImage] = useState(null);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "https://rv6p8wji3k.execute-api.ap-south-1.amazonaws.com/kode-kalesh/generateImage",
        form
      );

      console.log("Backend Response:", response.data);

      // Directly set the image URL
      setImage(response.data.imageUrl || null);

    } catch (error) {
      console.log("Error:", error.message);
      alert("Something went wrong while generating image.");
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
          Generate Image
        </h1>

        {/* INPUT GRID */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">

          {/* CATEGORY */}
          <motion.div
            whileHover={{ scale: 1.02 }}
            className="backdrop-blur-xl bg-white/10 p-5 border border-white/20 rounded-2xl shadow-lg"
          >
            <label className="block mb-2 text-sm text-gray-300">Category</label>
            <select
              name="category"
              value={form.category}
              onChange={handleChange}
              className="w-full p-3 rounded-xl bg-[#1b1b2d] text-white border border-white/20"
            >
              <option value="">Select Category</option>
              <option value="poster">Poster</option>
              <option value="logo">Logo</option>
              <option value="banner">Banner</option>
              <option value="thumbnail">YouTube Thumbnail</option>
              <option value="ad">Advertisement</option>
            </select>
          </motion.div>

          {/* PURPOSE */}
          <motion.div
            whileHover={{ scale: 1.02 }}
            className="backdrop-blur-xl bg-white/10 p-5 border border-white/20 rounded-2xl shadow-lg"
          >
            <label className="block mb-2 text-sm text-gray-300">Purpose</label>
            <select
              name="purpose"
              value={form.purpose}
              onChange={handleChange}
              className="w-full p-3 rounded-xl bg-[#1b1b2d] text-white border border-white/20"
            >
              <option value="">Select Purpose</option>
              <option value="marketing">Marketing</option>
              <option value="education">Education</option>
              <option value="entertainment">Entertainment</option>
              <option value="social_media">Social Media Post</option>
              <option value="branding">Branding</option>
            </select>
          </motion.div>

        </div>

        {/* TOPIC */}
        <motion.div
          whileHover={{ scale: 1.01 }}
          className="backdrop-blur-xl bg-white/10 p-6 border border-white/20 rounded-2xl shadow-lg"
        >
          <label className="block mb-2 text-sm text-gray-300">Topic</label>
          <input
            type="text"
            name="topic"
            value={form.topic}
            onChange={handleChange}
            placeholder="Enter image topic..."
            className="w-full p-3 mb-4 rounded-xl bg-[#1b1b2d] text-white border border-white/20"
          />
        </motion.div>

        {/* SUBMIT BUTTON */}
        <motion.button
          whileTap={{ scale: 0.97 }}
          whileHover={{ scale: 1.03 }}
          onClick={handleSubmit}
          className="w-full py-4 rounded-xl bg-blue-500/80 hover:bg-blue-600 text-white text-lg"
        >
          Generate Image
        </motion.button>

        {/* IMAGE RESULT */}
        {image && (
          <div className="mt-10 p-6 bg-white/10 border border-white/20 rounded-2xl flex justify-center">
            <img
              src={image}
              alt="Generated"
              className="rounded-xl shadow-xl max-w-full"
            />
          </div>
        )}

      </div>
    </div>
  );
};

export default GenImage;
