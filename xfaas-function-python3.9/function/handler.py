"""HTTP Cloud Function.
Args:
    request (flask.Request): The request object.
    <https://flask.palletsprojects.com/en/1.1.x/api/#incoming-request-data>
Returns:
    The response text, or any set of values that can be turned into a
    Response object using `make_response`
    <https://flask.palletsprojects.com/en/1.1.x/api/#flask.make_response>.
"""

import numpy as np


def function(request):
    random_number_generator = np.random.default_rng()
    random = random_number_generator.random(1)
    return "Random number: " + str(random[0])

