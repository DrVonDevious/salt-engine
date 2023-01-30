#version 330

in vec3 out_color;
in vec2 out_uvs;

out vec4 fragColor;

uniform sampler2D sampler;
uniform vec2 cellOffset;
uniform vec4 backgroundColor;
uniform vec4 foregroundColor;

void main() {
    vec4 pixelColor = texture(sampler, out_uvs + cellOffset);

    if (pixelColor.r == 1.0 && pixelColor.g == 0.0 && pixelColor.b == 1.0) {
        fragColor = (backgroundColor / 255);
    } else {
        fragColor = (pixelColor * (foregroundColor / 255));
    }
}