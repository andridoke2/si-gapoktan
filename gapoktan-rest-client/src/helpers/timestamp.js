const timestamp = (timestamp) => {
  const date = new Date(parseInt(timestamp));
  return `${date.getDate()}/${
    date.getMonth() + 1
  }/${date.getFullYear()} | ${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`;
};

module.exports = { timestamp };
