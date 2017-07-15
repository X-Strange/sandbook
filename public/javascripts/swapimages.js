/**
 * Created by tkmaec0 on 5/8/14.
 */
function swapImage(name, logo) {
    console.log('Name: ' + name);
    document.getElementById(logo).src = "@routes.Assets.at('/images/" + name + "')";
}

//var retailer = '@retailer';
retailer = retailer.replace("'", '', 'g').replace(/&#x27;/g,"");
//var brand = '@brand';
console.log('Retailer:' + retailer);
console.log('Brand:' + brand);

if (retailer === 'Macys') {
    swapImage('macys-logo.png', 'retailer-logo');
} else if (retailer === 'Kohls') {
    swapImage('kohls-logo.png', 'retailer-logo');
} else if (retailer === 'JC Penney') {
    swapImage('jcpenney-logo.gif', 'retailer-logo');
} else if (retailer === 'Payless') {
    swapImage('payless-logo.gif', 'retailer-logo');
} else if (retailer === 'Zappos') {
    swapImage('zappos-logo.jpg', 'retailer-logo');
} else if (retailer === 'Target') {
    swapImage('target-logo.jpg', 'retailer-logo');
};

if (brand === 'Adidas') {
    swapImage('adidas-logo.jpg', 'brand-logo');
} else if (brand === 'Nike') {
    swapImage('nike-logo.jpg', 'brand-logo');
} else if (brand === 'Nunn Bush') {
    swapImage('nunnbush-logo.png', 'brand-logo');
} else if (brand === 'Dockers') {
    swapImage('dockers-logo.png', 'brand-logo');
} else if (brand === 'New Balance') {
    swapImage('nb-logo.gif', 'brand-logo');
} else if (brand === 'Asics') {
    swapImage('asics-logo.jpg', 'brand-logo');
} else if (brand === 'Converse') {
    swapImage('converse-logo.jpg', 'brand-logo');
} else if (brand === 'Crocs') {
    swapImage('crocs-logo.png', 'brand-logo');
} else if (brand === 'Keds') {
    swapImage('keds-logo.png', 'brand-logo');
} else if (brand === 'Skechers') {
    swapImage('skechers-logo.jpg', 'brand-logo');
};