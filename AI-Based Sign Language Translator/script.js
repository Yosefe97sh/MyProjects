let fileUpload = document.getElementById('loadImage')
let img = document.getElementById('image')
function softmax(arr) {
    return arr.map(function(value,index) { 
      return Math.exp(value) / arr.map( function(y /*value*/){ return Math.exp(y) } ).reduce( function(a,b){ return a+b })
    })
}
async function fetchData(){
    let response = await fetch('./labels.json');
    let data = await response.json();
    data = JSON.stringify(data);
    data = JSON.parse(data);
    return data;
}

 // here the data will be return.


// Initialize/Load model
async function initialize() {
    model = await tf.loadGraphModel('http://127.0.0.1:5500/TFJS/model.json');
}

async function predict() {
    // Function for invoking prediction
    let img = document.getElementById('image')
    let offset = tf.scalar(255)
    let tensorImg =   tf.browser.fromPixels(img).resizeNearestNeighbor([299, 299]).toFloat().expandDims();
    let tensorImg_scaled = tensorImg.div(offset)
    console.log(tensorImg_scaled)
    prediction = await model.predict(tensorImg_scaled).data();
   
    fetchData().then((data)=> 
        {
            prediction = softmax(prediction)
            predicted_class = tf.argMax(prediction)
            class_idx = Array.from(predicted_class.dataSync())[0]
            document.getElementById('class-type').innerHTML = data[class_idx]
            console.log(data)
            console.log(data[class_idx])
            console.log(prediction)

        }
    );
    
}



fileUpload.addEventListener('change', function(e){

    initialize().then( () => { 
        predict()
    })
})